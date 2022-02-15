package org.apache.log4j.config;


import java.util.Hashtable;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import java.util.Enumeration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Appender;

public class PropertyPrinterProduct {
	private int numAppenders = 0;
	private Hashtable appenderNames = new Hashtable();

	public String genAppName() {
		return "A" + numAppenders++;
	}

	/**
	* Prints the configuration of the default log4j hierarchy as a Java properties file on the specified Writer. <p>N.B. print() can be invoked only once!
	*/
	public void print(PrintWriter out, PropertyPrinter propertyPrinter) {
		propertyPrinter.printOptions(out, Logger.getRootLogger());
		Enumeration cats = LogManager.getCurrentLoggers();
		while (cats.hasMoreElements()) {
			propertyPrinter.printOptions(out, (Logger) cats.nextElement());
		}
	}

	/**
	* @since  1.2.15
	*/
	public void printOptions(PrintWriter out, Category cat, PropertyPrinter propertyPrinter) {
		Enumeration appenders = cat.getAllAppenders();
		Level prio = cat.getLevel();
		String appenderString = (prio == null ? "" : prio.toString());
		while (appenders.hasMoreElements()) {
			Appender app = (Appender) appenders.nextElement();
			String name;
			if ((name = (String) appenderNames.get(app)) == null) {
				if ((name = app.getName()) == null || isGenAppName(name)) {
					name = genAppName();
				}
				appenderNames.put(app, name);
				printOptions(out, app, "log4j.appender." + name, propertyPrinter);
				if (app.getLayout() != null) {
					printOptions(out, app.getLayout(), "log4j.appender." + name + ".layout", propertyPrinter);
				}
			}
			appenderString += ", " + name;
		}
		String catKey = (cat == Logger.getRootLogger()) ? "log4j.rootLogger" : "log4j.logger." + cat.getName();
		if (appenderString != "") {
			out.println(catKey + "=" + appenderString);
		}
		if (!cat.getAdditivity() && cat != Logger.getRootLogger()) {
			out.println("log4j.additivity." + cat.getName() + "=false");
		}
	}

	/**
	* Returns true if the specified appender name is considered to have been generated, that is, if it is of the form A[0-9]+.
	*/
	public boolean isGenAppName(String name) {
		if (name.length() < 2 || name.charAt(0) != 'A')
			return false;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) < '0' || name.charAt(i) > '9')
				return false;
		}
		return true;
	}

	public void printOptions(PrintWriter out, Object obj, String fullname, PropertyPrinter propertyPrinter) {
		out.println(fullname + "=" + obj.getClass().getName());
		PropertyGetter.getProperties(obj, propertyPrinter, fullname + ".");
	}
}