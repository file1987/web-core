package com.studio.elephant.web.framework;

import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.studio.elephant.utils.clazz.ClassUtil;
import com.studio.elephant.web.framework.annotation.ServiceMapping;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //assertTrue( true );
    	/**
    	ServiceConfig config = new ServiceConfig();
    	assertTrue(!config.getServices().isEmpty());**/
    	String packageName ="com.studio.elephant.web.framework";
    	
    	//Package _package = Package.getPackage(packageName);
    	
    	Set<Class<?>> clazzs = ClassUtil.getClasses(packageName);
    	if(clazzs==null||clazzs.isEmpty()){
    		System.out.println("fuck");
    		return ;
    	}
    	for(Class<?> clazz:clazzs){
    		ServiceMapping mapping = clazz.getAnnotation(ServiceMapping.class);
    		if(mapping!=null){
    			System.out.println("  class:"+clazz.getName() + "  serviceCode:"+ mapping.serviceCode() );
    		}
    	}
    	
    	
    	
    	
    	
    }
}
