# Parking APP based on OOP concepts

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-parking-app">About Parking App</a>
    </li>
    <li>
      <a href="#objects">Objects</a>
      <ul>
        <li><a href="#parkingSpot">ParkingSpot</a></li>
        <li><a href="#vehicle">Vehicle</a></li>
        <li><a href="#customer">Customer</a></li>
        <li><a href="#reservation">Reservation</a></li>
      </ul>
    </li>
    <li><a href="#domain-model">Domain Model</a></li>
    <li><a href="#class-diagram">Class Diagram</a></li>
  </ol>
</details>

# About Parking App
Parking rental application to streamline parking management.

## Objects

## ParkingSpot
- Represents an **individual parking spot** within a parking lot or parking area.
- **Attributes**:
  - `spotNumber: int` The identifier for the parking spot.
  - `occupied: boolean` Indicates whether the spot is occupied.

### Vehicle
- It represents an **individual vehicle** that a **customer** owns.
- **Attributes**:
  - `licensePlate: String` The license plate of the vehicle, also works as identifier.
  - `type: VehicleType` The type of vehicle, defined by enumeration VehicleType.

### Customer
- It represents an **individual customer**.
- **Attributes**:
  - `id: int` The identifier for the customer.
  - `name: String` The name of the customer.
  - `phoneNumber: String` The phone number belonging to the customer.
  - `reservation: Reservation` The reservation made by the customer, is ***null*** if the customer doesn't have an active reservation.

### Reservation
- It represents an **individual reservation** made by a **customer**.
- **Attributes**:
  - `id: String` The identifier for the reservation.
  - `customer: Customer` The customer who makes the reservation.
  - `parkingSpot: ParkingSpot` The parking spot that is being reserved.
  - `startTime: LocalDateTime` The time when the reservation starts.
  - `endTime: LocalDateTime` The time when the reservation ends.
  - `associatedVehicle: Vehicle` The vehicle associated with the reservation.

## Domain Model
![domain model](img/domain-model.jpeg)

## Class Diagram
![class model](img/class-diagram.jpeg)
