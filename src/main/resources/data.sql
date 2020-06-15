INSERT INTO TRAVELAGENCIES (NAME, CITY, PHONE_NUMBER)  VALUES
  ('ITAKA', 'Warszawa', '123-123-456'),
  ('RAINBOW', 'Kraków', '574-456-344'),
  ('TUI', 'Wrocław', '344-233-455');

INSERT INTO CUSTOMERS (FIRST_NAME, LAST_NAME, LOGIN, EMAIL)  VALUES
  ('Marcin', 'Turczyn', 'marcinos', 'mar123@gmail.com'),
  ('Marcin', 'Krakowiak', '1234', 'markrak@gmail.com'),
  ('Kamil', 'Zawada', 'python123', 'kamil123@gmail.com'),
  ('Marcin', 'Stelmach', 'stel', 'slet123@gmail.com');

INSERT INTO HOTELS (HOTEL_NAME, CITY, STARS, PHONE_NUMBER)  VALUES
  ('VIXTORIA', 'Warsaw', 'THREE', '123-123-456'),
  ('ODYSEJA', 'Kraków', 'FIVE', '123-654-456'),
  ('ZEUS', 'Wrocław', 'TWO', '123-234-456'),
  ('ATENA', 'Lublin', 'FOUR', '123-852-456');

INSERT INTO ADDRESSES (CUSTOMER_ID,
  STREET, HOUSE_NR, CITY, ZIP_CODE, PHONE_NR)  VALUES
  ('1', 'Lubelska', '5', 'Warsaw', '22-304', '123-123-456'),
  ('3', 'Radziszewska', '5c', 'Kraków', '23-123', '566-123-456'),
  ('4', 'Lwowska', '54', 'Wrocław', '12-345', '345-123-456'),
  ('2', 'Krótka', '51', 'Lublin', '12-203', '111-123-456');
