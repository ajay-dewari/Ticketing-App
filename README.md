# Ticketing-App

## What is the app about:
Ticketing-App is a app that render the seatMap of movie theaters, this app have build in support for Material-3 which allows the  `dynamic-themeing`  coloring of all the UI component including the iocons and font text color and style, this android uses all the latest tech stack of android development.

## Architecture Library included
The app is build on a single activity architecture follows the MVI clean architecture with the latest android architecture components and jetpack libraries. The app uses `Jetpack compose` for UI elements, `Coroutines` for concurrency, `Retrofit-2` for network calls, `Hilt` for the DI, and for `compose-navigation` to navigation throughout the app.

## Application overview:
The application has two screen `HomeScreen` and `SeatSelectionScreen`, the home screen is an empty screen that trigger the navigation to the SeatSelectionScreen, the SeatSelectionScreen render a seat map dynamicly generated from the rest full api and allow the users to selecte and enter the required seats.

**UseCases :**
- Call a network api call to dynamicaly populate a seatMap.
- Allow user to enter the number of seats he wantas to book.
- Show the user which ticket are sold and which ticket are available for booking.
- Show the user which ticket he has selected.
- Not allow the user to select more number of sets than the required number of seats.
- Show the indicatator at bottom to identify the type of seat(Available, sold, selected)
- Make the map scrollable.
- Users should not be able to select an already booked seat.
- The next button should be enabled only when the required number of seats are selected.
- All icons can are taken for material design.

**Test Coverage :**
- The test coverage of the project is `90%` excluding the UI components.
- The test report can be generated with `./gradlew test`.
- Tests are covered for `data`, `repository`, `usecases` and `viewModel`


Screen:

![ticket-gallery](https://github.com/ajay-dewari/Ticketing-App/blob/master/ticketing-app.gif)



