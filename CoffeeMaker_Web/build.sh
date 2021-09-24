

# cd ~/MP1/mp1-delongmeng/CoffeeMaker_Web

mkdir -p target/CoffeeMaker_Web/WEB-INF/classes/
javac -d target/CoffeeMaker_Web/WEB-INF/classes/ src/main/java/edu/ncsu/csc326/coffeemaker/exceptions/*.java src/main/java/edu/ncsu/csc326/coffeemaker/*.java
cp src/main/webapp/*.jsp target/CoffeeMaker_Web/
cp src/main/webapp/WEB-INF/web.xml target/CoffeeMaker_Web/WEB-INF/
cd target/CoffeeMaker_Web
jar -cvf ../CoffeeMaker_Web.war *

