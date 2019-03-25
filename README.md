Jackson module for handling cyclic references while serializing.

### Usage

#### Maven Dependency

To use module on maven based project, use following dependency:

    <dependency>
      <groupId>com.dilipkumarg.projects</groupId>
      <artifactId>jackson-cyclic-ref-handler-module</artifactId>
      <version>1.0</version>
    </dependency>   

#### Registering Module

Like all standard Jackson modules (libraries that implement Module interface), registration is done as follows:

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new CircularLoopHandlingJacksonModule());

### Configuration Options

`CircularLoopHandlingJacksonModule` accepts the `CLHandlingConfiguration` object to configure the following properties:
* `failOnCircularReferences` is a flag to enable throwing error if cyclic reference detected. If it is marked as 
`false` it'll ignore serialzing the referenced object.
* `replacementForCircularReference` If cyclic reference detected and `failOnCircularReferences` as marked as `false` 
then this value will be taken for seializing that reference.

