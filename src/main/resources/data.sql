INSERT INTO Customer (name, user_name, email, adress, phone)
VALUES ('Gertrud', 'Gert', 'gertrud@info.se', 'Gåsvägen 13, 66194 Ankeborg', 066277452),
       ('Gudrun', 'Gud', 'gudrun@info.se', 'Paradisäppelvägen 111, 66194 Ankeborg', 0702447533),
       ('Spräcklis', 'spräcken', 'spräcklis_anka@gmail.com', 'Persikogatan 101, 66194 Ankeborg', 0709864123),
       ('Anki', 'ankis', 'anki123@hotmail.com', 'Tomtebacken, 66194 Ankeborg', '0734586425'),
       ('Pytte', 'e-ttyp', 'pytte@yahoo.com', 'Stenvägen 10, 66194 Ankeborg', '0709010356');

INSERT INTO Car (plate_no, price, manufacturer, model, booked)
VALUES ('OOP877', 255, 'Audi', 'A1', false),
       ('UBB771', 677, 'Koningsegg', 'Regera', false),
       ('PLZ945',450,'BMW','Isetta','false'),
       ('XYZ537',350,'Peel','P50','false'),
       ('AAP177',2000,'International','CXT','false');


INSERT INTO Booking (start_date, end_date, customer_id, car_id, booked)
VALUES ('2024-07-25', '2024-07-29', 1, 1, true),
       ('2024-05-09','2024-05-11',3,3,true),
       ('2024-06-20','2024-06-30',4,5,true);

