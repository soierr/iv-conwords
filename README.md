# iv-conwords

Application is for demonstration of concatenated words searching 

### Install and run

To run the application please invoke `java -jar iv-conwords-1.0.jar` from project root folder.

For assembling the application from source please run `mvn install` from project root folder.    
The `iv-conwords-1.0.jar` will be recreated. The `words.txt` file needs to be in project root folder.


Launched and compiled with with help of:

    Apache Maven 3.3.9
    Maven home: --//--
    Java version: 1.8.0_161, vendor: Oracle Corporation
    Java home: --//--
    Default locale: en_US, platform encoding: Cp1251
    OS name: "windows 7", version: "6.1", arch: "amd64", family: "dos"

    java version "1.8.0_161"  
    Java(TM) SE Runtime Environment (build 1.8.0_161-b12)  
    Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)

### Implementation approach

The main idea is to split the data into ranges.    
Every range holds the words started from the specific letter. It makes possible to process data in the multithreaded environment.   
Every word in range is processed with help of recursion. More details might be found as comments alongside the code.

