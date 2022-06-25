# Assignment


## vanilla

Analyzed the Singleton pattern used in DriverFactory and URLFactory and 
I have used the same base starter kit framework to build this exercise tests. 
 

## Page Layer :
Achieved page layer management as per assignment instruction

HomePage.java,
TelevisionPage.java,
ResultPage.java,
ProductDetailsPage.java

## Helper Methods:

created GenericWrappers.java which will contains generic selenium wrappers for all pages.

i.e click , scroll, mouseover action 

All pages extends these wrapper class to use respective functions

## Test Layer:

TestAmazon.java covers all test which mentioned as scenario in assignment.
 
I have used same org.junit.jupiter.api for assertion.
Besides that added @TestMethodOrder, @TestInstance, @BeforeAll,@AfterAll,@Order to ran test in single class file

## Logger:
Used same org.slf4j.Logger

Under src/main/resources, created log4j2.xml for configuration of log in console for all Status

## To Execute
Added <junit-jupiter-engine> in pom.xml to make test execution ran in order given by user


   1.Right click on TestAmazon.java ‡ Ran as Junit Test

Second way via,Right Click on Project root folder‡Ran as  Maven test


## Success

Able to ran successfully in chrome, all scenarios got passed

## Github Config

created repo in Github
initilazed git in project path
added remote origin to local repo
Imported local project to my github created repo 