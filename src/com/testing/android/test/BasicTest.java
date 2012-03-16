package com.testing.android.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;

import com.jayway.android.robotium.solo.Solo;


@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class BasicTest extends ActivityInstrumentationTestCase2{

private static String TARGET_PACKAGE_ID = "com.testing.android"; 
private static String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.testing.android.LibraryHomeActivity"; 

private static Class launcherActivityClass;
static{

try 
{ 
launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME); 
} catch (ClassNotFoundException e){ 
throw new RuntimeException(e); 
} 
}

 public BasicTest()throws ClassNotFoundException{ 
 super(TARGET_PACKAGE_ID,launcherActivityClass);
 } 
 
 private Solo solo;

 
 @Override 
  protected void setUp() throws Exception 
  { 
  solo = new Solo(getInstrumentation(),getActivity());
  }
 public void test1() { 
	 
		//Verify Text present in the view
	 ArrayList<Button> buttons = solo.getCurrentButtons();
		assertEquals("Expected 5 buttons on the homescreen", 5, buttons.size());
		for (Button button:  buttons) {
			Log.i("button text:", (String) button.getText());
			}
		
		 assertTrue(solo.searchText("List of Books"));
		 assertTrue(solo.searchText("Add Member")); 	
		 assertTrue(solo.searchText("Add Book"));
		 assertTrue(solo.searchText("Check Out")); 
		 assertTrue(solo.searchText("Check In")); 
		 
		 }
 
 public void test2() { 
	 
		//Adding a member
	 solo.clickOnButton("Add Member");
	 assertTrue(solo.searchText("Enter Name")); 
	 assertTrue(solo.searchText("Phone Number")); 

 }
 
 public void test3() { 
	 
		//Adding a member
	 solo.clickOnButton("Add Member");
	 solo.enterText(0, "Venkat");
	 solo.enterText(1, "123");
	 solo.clickOnButton("Save");
}
 
 public void test4() { 
	 
		//Adding a member
	 solo.clickOnButton("Add Book");
	 assertTrue(solo.searchText("Book Name")); 
	 assertTrue(solo.searchText("Author name")); 

}
 
 public void test5() { 
	 
		//Adding a book
	 solo.clickOnButton("Add Book");
	 solo.enterText(0, "Android");
	 solo.enterText(1, "Katra");
	 solo.clickOnButton("Save");
	 
		solo.clickOnButton("List of Books");
		assertTrue(solo.searchText("Android")); 
		assertTrue(solo.searchText("Katra")); 
}
 
 public void test7() { 	 
	 
	 //Finding a Book
	solo.clickOnButton("List of Books");
	assertFalse(solo.searchText("Android")); 
	assertFalse(solo.searchText("Katra")); 
		 
				 
	 }
 
 
 public void test6() { 
	 
		//Adding a member
	 solo.clickOnButton("Check Out");
	 solo.clickOnButton("Check In");
	 //assertTrue(solo.searchText("Enter Name")); 
	 //assertTrue(solo.searchText("Phone Number")); 

}

 
 @Override
 public void tearDown() throws Exception {
 solo.finishOpenedActivities();
 }
 }