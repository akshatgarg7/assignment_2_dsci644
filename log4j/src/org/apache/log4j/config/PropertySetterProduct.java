package org.apache.log4j.config;


import java.util.Properties;
import java.util.Enumeration;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.Appender;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.helpers.LogLog;
import java.lang.reflect.InvocationTargetException;
import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.apache.log4j.spi.ErrorHandler;

public class PropertySetterProduct {
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	* Set the properites for the object that match the <code>prefix</code> passed as parameter.
	*/
	public void setProperties(Properties properties, String prefix, PropertySetter propertySetter) {
		int len = prefix.length();
		for (Enumeration e = properties.propertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			if (key.startsWith(prefix)) {
				if (key.indexOf('.', len + 1) > 0) {
					continue;
				}
				String value = OptionConverter.findAndSubst(key, properties);
				key = key.substring(len);
				if (("layout".equals(key) || "errorhandler".equals(key)) && obj instanceof Appender) {
					continue;
				}
				PropertyDescriptor prop = propertySetter.getPropertyDescriptor(Introspector.decapitalize(key));
				if (prop != null && OptionHandler.class.isAssignableFrom(prop.getPropertyType())
						&& prop.getWriteMethod() != null) {
					OptionHandler opt = (OptionHandler) OptionConverter.instantiateByKey(properties, prefix + key,
							prop.getPropertyType(), null);
					PropertySetter setter = new PropertySetter(opt);
					setter.setProperties(properties, prefix + key + ".");
					try {
						prop.getWriteMethod().invoke(this.obj, new Object[] { opt });
					} catch (IllegalAccessException ex) {
						LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
					} catch (InvocationTargetException ex) {
						if (ex.getTargetException() instanceof InterruptedException
								|| ex.getTargetException() instanceof InterruptedIOException) {
							Thread.currentThread().interrupt();
						}
						LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
					} catch (RuntimeException ex) {
						LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
					}
					continue;
				}
				setProperty(key, value, propertySetter);
			}
		}
		activate();
	}

	/**
	* Set a property on this PropertySetter's Object. If successful, this method will invoke a setter method on the underlying Object. The setter is the one for the specified property name and the value is determined partly from the setter argument type and partly from the value specified in the call to this method. <p>If the setter expects a String no conversion is necessary. If it expects an int, then an attempt is made to convert 'value' to an int using new Integer(value). If the setter expects a boolean, the conversion is by new Boolean(value).
	* @param name     name of the property
	* @param value    String value of the property
	*/
	public void setProperty(String name, String value, PropertySetter propertySetter) {
		if (value == null)
			return;
		name = Introspector.decapitalize(name);
		PropertyDescriptor prop = propertySetter.getPropertyDescriptor(name);
		if (prop == null) {
			LogLog.warn("No such property [" + name + "] in " + obj.getClass().getName() + ".");
		} else {
			try {
				setProperty(prop, name, value);
			} catch (PropertySetterException ex) {
				LogLog.warn("Failed to set property [" + name + "] to value \"" + value + "\". ", ex.rootCause);
			}
		}
	}

	/**
	* Set the named property given a  {@link PropertyDescriptor} .
	* @param prop  A PropertyDescriptor describing the characteristics of the property to set.
	* @param name  The named of the property to set.
	* @param value  The value of the property.      
	*/
	public void setProperty(PropertyDescriptor prop, String name, String value) throws PropertySetterException {
		Method setter = prop.getWriteMethod();
		if (setter == null) {
			throw new PropertySetterException("No setter for property [" + name + "].");
		}
		Class[] paramTypes = setter.getParameterTypes();
		if (paramTypes.length != 1) {
			throw new PropertySetterException("#params for setter != 1");
		}
		Object arg;
		try {
			arg = convertArg(value, paramTypes[0]);
		} catch (Throwable t) {
			throw new PropertySetterException("Conversion to type [" + paramTypes[0] + "] failed. Reason: " + t);
		}
		if (arg == null) {
			throw new PropertySetterException("Conversion to type [" + paramTypes[0] + "] failed.");
		}
		LogLog.debug("Setting property [" + name + "] to [" + arg + "].");
		try {
			setter.invoke(obj, new Object[] { arg });
		} catch (IllegalAccessException ex) {
			throw new PropertySetterException(ex);
		} catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof InterruptedException
					|| ex.getTargetException() instanceof InterruptedIOException) {
				Thread.currentThread().interrupt();
			}
			throw new PropertySetterException(ex);
		} catch (RuntimeException ex) {
			throw new PropertySetterException(ex);
		}
	}

	/**
	* Convert <code>val</code> a String parameter to an object of a given type.
	*/
	public Object convertArg(String val, Class type) {
		if (val == null)
			return null;
		String v = val.trim();
		if (String.class.isAssignableFrom(type)) {
			return val;
		} else if (Integer.TYPE.isAssignableFrom(type)) {
			return new Integer(v);
		} else if (Long.TYPE.isAssignableFrom(type)) {
			return new Long(v);
		} else if (Boolean.TYPE.isAssignableFrom(type)) {
			if ("true".equalsIgnoreCase(v)) {
				return Boolean.TRUE;
			} else if ("false".equalsIgnoreCase(v)) {
				return Boolean.FALSE;
			}
		} else if (Priority.class.isAssignableFrom(type)) {
			return OptionConverter.toLevel(v, (Level) Level.DEBUG);
		} else if (ErrorHandler.class.isAssignableFrom(type)) {
			return OptionConverter.instantiateByClassName(v, ErrorHandler.class, null);
		}
		return null;
	}

	public void activate() {
		if (obj instanceof OptionHandler) {
			((OptionHandler) obj).activateOptions();
		}
	}
}