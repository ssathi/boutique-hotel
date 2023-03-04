# boutique-hotel
A project for Telepathy AI

# Instructions

1. Install JDK 17 or later
2. Run './mvnw clean test' to run the test cases
3. Run './mvnw clean install' to run the test cases and build the runnable jar
4. After successful build, run 'java -jar target/boutique-hotel-1.0.jar'


# Testing the App
Open another terminal to perform below tests

1. To check in, run 'curl -X POST http://localhost:9999/hotel/room/checkin'
2. To check out, replace 'ROOM ID' from previous command and run 'curl -X POST http://localhost:9999/hotel/room/{ROOM ID}/checkout'
e.g. curl -X POST http://localhost:9999/hotel/room/1A/checkout

3. To clean, replace 'ROOM ID' from previous command and run 'curl -X POST http://localhost:9999/hotel/room/{ROOM ID}/clean'
e.g. curl -X POST http://localhost:9999/hotel/room/1A/clean

4. To mark a room for repair, replace 'ROOM ID' from previous command and run 'curl -X POST http://localhost:9999/hotel/room/1A/outofservice'
e.g. curl -X POST http://localhost:9999/hotel/room/1A/outofservice

5. To query all available rooms, run 'curl  http://localhost:9999/hotel/room'

