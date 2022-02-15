/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j.config;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

/**
   Prints the configuration of the log4j default hierarchy
   (which needs to be auto-initialized) as a propoperties file
   on a {@link PrintWriter}.
   
   @author  Anders Kristensen
 */
public class PropertyPrinter implements PropertyGetter.PropertyCallback {
  private PropertyPrinterProduct propertyPrinterProduct = new PropertyPrinterProduct();
protected Hashtable layoutNames   = new Hashtable();
  protected PrintWriter out;
  protected boolean doCapitalize;
  
  public
  PropertyPrinter(PrintWriter out) {
    this(out, false);
  }
  
  public
  PropertyPrinter(PrintWriter out, boolean doCapitalize) {
    this.out = out;
    this.doCapitalize = doCapitalize;
    
    propertyPrinterProduct.print(out, this);
    out.flush();
  }
  
  protected
  String genAppName() {
    return propertyPrinterProduct.genAppName();
  }
  
  /**
   * Returns true if the specified appender name is considered to have
   * been generated, that is, if it is of the form A[0-9]+.
  */
  protected
  boolean isGenAppName(String name) {
    return propertyPrinterProduct.isGenAppName(name);
  }
  
  /**
   * Prints the configuration of the default log4j hierarchy as a Java
   * properties file on the specified Writer.
   * 
   * <p>N.B. print() can be invoked only once!
   */
  public
  void print(PrintWriter out) {
    propertyPrinterProduct.print(out, this);
  }
  
  /**
   * @since 1.2.15
   */
  protected
  void printOptions(PrintWriter out, Category cat) {
    propertyPrinterProduct.printOptions(out, cat, this);
  }

  protected void printOptions(PrintWriter out, Logger cat) {
      propertyPrinterProduct.printOptions(out, (Category) cat, this);
  }
  
  protected
  void printOptions(PrintWriter out, Object obj, String fullname) {
    propertyPrinterProduct.printOptions(out, obj, fullname, this);
  }
  
  public void foundProperty(Object obj, String prefix, String name, Object value) {
    // XXX: Properties encode value.toString()
    if (obj instanceof Appender && "name".equals(name)) {
      return;
    }
    if (doCapitalize) {
      name = capitalize(name);
    }
    out.println(prefix + name + "=" + value.toString());
  }
  
  public static String capitalize(String name) {
    if (Character.isLowerCase(name.charAt(0))) {
      if (name.length() == 1 || Character.isLowerCase(name.charAt(1))) {
        StringBuffer newname = new StringBuffer(name);
        newname.setCharAt(0, Character.toUpperCase(name.charAt(0)));
        return newname.toString();
      }
    }
    return name;
  }
  
  // for testing
  public static void main(String[] args) {
    new PropertyPrinter(new PrintWriter(System.out));
  }
}
