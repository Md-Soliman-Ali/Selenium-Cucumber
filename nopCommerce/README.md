 
 Feature (name of the feature)
 
 Scenario (name of the scenario)
 
- Given: Step is used to set the context
 
- When: Step is the action/interaction with the system
 
- Then: Step for representing the outcome
 
- And: Step for extending the previous step

---

- Run All Features: "Features/"
- Run Single Features: "Features/Customers.feature"
- Run Multi Features: {"Features/Customers.feature", "Features/Login.feature"}
- Run Multi Tag: "@regression", "@sanity"

# How to run this project (Maven)

-  Configure MAVEN_HOME (if needed)
-  Clone this project
-  Open pom.xml as a project by IntellIJ

```sh
Terminal: mvn clean install
```

# How to run this project (Jenkins)

- Manage Jenkins -> Global Tool Configuration -> JDK installations (Name, JAVA_HOME), Maven (Name, MAVEN_HOME) -> Apply

- New Item -> Maven Project -> OK -> Build Triggers (Root POM {POM Location}), (Goals and options {clean install}) -> Apply




 
