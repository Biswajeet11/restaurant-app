# Request calls
1. GET /v1/reservations/ - Retrieves all reservations
2. GET /v1/reservations/{id} -Retrieves a specific reservation based on ID
3. POST /v1/reservations/ - Creates a new reservation
4. PUT /v1/reservations/{id} - Updates an existing reservation
5. DELETE /v1/reservations/{id} - Removes a specific reservation based on ID


# Post/Update Call Sample Data:
{
"restaurantName": "Barbeque Nation",
"partySize": 4 ,
"user": "sample User",
"reservationDate": "2022-10-13" (date-format: yyyy-mm-dd)
}