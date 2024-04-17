INSERT INTO Customer (name, user_name,email,adress,phone)
VALUES ('Gertrud','Gert','gertrud@info.se','Gåsvägen 13 66194 Ankeborg',066277452),
       ('Gudrun','Gud','gudrun@info.se','Paradisäppelvägen 111 66194 Ankeborg', 0702447533);

INSERT INTO Car (plate_no,price,manufacturer,model,booked) VALUES ('OOP877',255,'Audi','A1',true),
                ('UBB771',677,'Koningsegg','Regera',false);

INSERT INTO Booking (date,customer_id,car_id) VALUES ('2017-07-25',1,1);

--INSERT INTO Customer (name) VALUES ('Gertrud');


--       ('Persikogatan 101', 66194, 'Ankeborg'),--kajsa
--       ('Tomtebacken', 66194, 'Ankeborg'),-- joakim
--       ('Stenvägen 10', 66194, 'Ankeborg'); --musse pigg


