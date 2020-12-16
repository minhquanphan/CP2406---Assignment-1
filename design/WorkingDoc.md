# Java Based Traffic Simulator
## Program Working Document

### Specification
The main problem that needs to be solved is to simulate traffic in city areas. More specifically, it needs to simulate
different vehicle types behavior with traffic lights, roads and intersections.

This program is built as a console app that takes inputs from users, simulates a single car moving along a single lane
road, interacts with a traffic light, moves to a second road connected to the first, representing a very basic
intersection, and moves to the end of that road. It will display the status of objects within the simulation to the
users along the way.

### Decomposition
There will be 6 classes that need to interact with each other for the program to run properly as described above in
the Specification section: Car, Bus, Motorbike, Road, Traffic Light and finally the Main class.
The detailed attributes and methods are explained further below:
#### Car
The car class will be an object that describes an average size road vehicle. Holding the following attributes;
- *id* – a unique identifier that will differentiate each car.
- *name* - name of the car.  
- *length* – the physical space the car occupies longways.
- *breadth* – the physical space the car occupies widthways, half the cars length.
- *speed* – how far the car moves for each simulation turn.
- *position* – where the car is located on a road.
- *currentRoad* – the road the car is currently traveling on.
- *currentCarNumber* - this is to track the car number incrementally generated, to make sure car id is unique

The car will be able to move, using the move() method, within the simulation along a road depending on its speed, 
the speed will be defined by the speed limit of the road the car is traveling on. When the car is in the same position 
as a traffic light it will check its state before moving, if the light is red it will stop, if the light is green it 
will move to the next road. When the cars position is equal to the end of a road and there is no connected road it will stop ending the simulation.

##### Bus
The bus class will be a subclass of car, describing a large road vehicle. 
It will inherit its attributes and behaviour from Car except its length will be defined as being three times that of the car’s length.

##### Motorbike
The motorbike class will be a subclass of car, describing a small road vehicle. 
It will inherit its attributes from and behaviour Car except its length will be defined as being half that of the car’s length.

#### Road
The road class will be an object that describes a single lane road. Holding the following attributes;
- *id* - a unique identifier that will differentiate each road
- *name* - name of road  
- *speedLimit* – the maximum speed that cars on that road may travel at.
- *length* – the number of segments the road is comprised of and the physical space it occupies.
- *startLocation* – the (x,y) coordinate that represents where the road begins.
- *endLocation* – the (x,y) coordinate that represents where the road ends.
- *connectedRoads* – all of the roads that this road is physically connected to.
- *lightsOnRoad* – all the traffic lights that are on the ends this road.
- *carOnRoad* – all of the cars that are currently traveling on this road.
- *currentVehiclesLength* - track the total length of all vehicles travelling on that road. This is to help
  validate whether a new vehicle can enter this road or not
- *currentRoadNumber* - this is to track the road number incrementally generated, to make sure road id is unique

For the first version of the program, the speed will be constant and set to 1. Meaning the car will only be able to
move a single position each turn making it easier deal with traffic lights and the ends of roads. The length of the road
will be variable depending of user input. Roads will interact with other roads by being connected to them, creating a
very basic intersection with only one option for the car to take. Cars will move along the road from the starting
position (1) to the end position, that depends on the road’s length. Traffic lights can be placed at only the end
position of the road. The endLocation of a road will be the startLocation of the next road which is connected to the first road.
In this version, roads are all horizontal and the endLocation of a road will be automatically deduced from the startLocation.

***Enhancement***:
1) Bus will be validated before entering any road. The validation works
   such that bus can only enter roads having length of at least double, at most 5 times that bus's length.
   Any buses which do not satisfy this rule will be disallowed from entering those roads.
2) New vehicles will be blocked from entering any road if that road is full of vehicles, i.e the
   total length of all the vehicles travelling on that road is equal to or exceeds the road length. For
   example, the road A has length 10m on which there are 2 buses, each with length 3m, and 4 other cars,
   each with length 1m, travelling. The new motorbike won't be able to enter this road based on this rule, until
   there is at least 1 of the 6 vehicles moving to another road, leaving empty space on this road for
   the motorbike to enter.
3) Validation is added so that the speedLimit, length and locations must not be negative.
4) There is only a method to get road id. Once the road id is set in the constructor, 
   it can not be set 1 more time later again. This rule is also applied to other classes.
5) Cars will be validated against its uniqueness on a road such that if the system or users somehow add
the same car again on the same road, the program will throw exception preventing that from hapenning.
6) Locations are also validated such that it must contain 2 coordinates (x-coordinate, y-coordinate), represented
as a 2-dimensional array. Each coordinate value must not be negative.
#### Traffic Light
The traffic light class will represent a simple red or green traffic light. Holding the following attributes;
- *id* - a unique identifier that will differentiate each traffic light.
- *state* - the colour the light is displaying.
- *position* - where the traffic light is located on the road.
- *roadAttachedTo* - the road that the light is attached to.
- *currentLightNumber* - this is to track the light number incrementally generated, to make sure light id is unique

The traffic light will operate, using the operate() method. Randomly changing from green to red. This operation will
be based on pseudo-random numbers generated by the program. The light will be placed on a road only at its final
position, the end of the road, and will interact with cars that are also at that position. If the light is red the cars
will stop and not move to the next road. If the light is green the cars will continue past and move to the next road.

### Main
This class will have the main() method that will contain the simulation loop.
All the objects needed for the simulation will be created here; creating 2 roads, a car and a traffic light. Locations
for roads and their connections will also be set here. For this version of the program the user will only have control
over the length of the road and the speed the simulation runs at. The simulation will run, moving the car and operating
traffic lights, until the car can no longer move and has come to a stop.
