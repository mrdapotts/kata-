Test code

Build instructions



mvn clean compile test  assembly:single

Runs a test suit and creates a file jar

java   -jar  target/kata-0.0.1-SNAPSHOT.jar   /tmp/data

Where data is 

A
A
A
A
A
A
A
A
D
D

The generated results will be   390


Can be run under eclipse

./src/main/java/uk/co/kata/rules/RuleFactory.java
Creates new routes 
./src/main/java/uk/co/kata/rules/RuleUtils.java
Support utils for rules
./src/main/java/uk/co/kata/rules/MinRule.java
Rule Implementation file for simple rule
./src/main/java/uk/co/kata/rules/BaseRule.java
Base rule  Implementation 
./src/main/java/uk/co/kata/rules/Rules.java
Read and load Rules
./src/main/java/uk/co/kata/rules/ItemType.java
Class file an an item
./src/main/java/uk/co/kata/rules/FullRule.java
Rule Implementation file for full  rule
./src/main/java/uk/co/kata/driver/Kata.java
Driver program 
./src/test/java/uk/co/kata/BasicTest.java
Test program

./src/main/resources/logging.properties
Log file config
./src/main/resources/rules.properties
Default items file  


Testfile configs
./src/test/resources/combo.text
./src/test/resources/2A.text
./src/test/resources/empty.text
./src/test/resources/3A.text
./src/test/resources/unknown.text
./src/test/resources/4D.text
./src/test/resources/4A.text
./target/test-classes/combo.text
./target/test-classes/2A.text
./target/test-classes/empty.text
./target/test-classes/3A.text
./target/test-classes/unknown.text
./target/test-classes/4D.text
./target/test-classes/4A.text

Existing program only supports two rules.

If more rules are needed the file BaseRule will need to be subclassed, see FullRule or MinRule as way of doing this.

 A checkArgumet and processData methods need to be added.
 The rule factory will have to be updated to uderstand the new rule.
 The new rule will then be invoked as and when required.
 
 There is a test suit of 11 basic tests, which could be improved.
