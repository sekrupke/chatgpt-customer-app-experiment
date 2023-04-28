import csv
import mysql.connector


def connect_to_database():
    """Connects to the database and returns a cursor object"""
    conn = mysql.connector.connect(
        host="localhost",
        port=3306,
        database="customerDB",
        user="admin",
        password="password1234!ABC"
    )
    return conn


def close_database_connection(conn):
    """Commits changes and closes the database connection"""
    conn.commit()
    conn.close()


def import_customers(conn):
    """Imports customer data from a CSV file into a MySQL database"""
    with open('customers.csv', newline='', encoding='utf-8-sig') as csvfile:
        reader = csv.DictReader(csvfile, delimiter=',')
        for row in reader:
            id = int(row['Nr.'].strip())
            birth_date = format_date(row['Geburtsdatum'].strip())
            newsletter = True if row['Newsletter'].strip() == 'ja' else False
            street, house_number = split_street(row['Straße'].strip())
            data = format_data(row, id, birth_date, newsletter, street, house_number)
            insert_row(conn, data)


def format_date(date_string):
    """Formats a date string from DD.MM.YYYY to YYYY-MM-DD"""
    date_parts = date_string.split('.')
    return '-'.join([date_parts[2], date_parts[1], date_parts[0]])


def split_street(street):
    """Splits a street string into the street name and house number"""
    parts = street.split(' ')
    return ' '.join(parts[:-1]), parts[-1]


def format_data(row, id, birth_date, newsletter, street, house_number):
    """Formats the customer data for insertion into the database"""
    return (
        id,
        row['Anrede'].strip(),
        row['Titel'].strip(),
        row['Vorname'].strip(),
        row['Nachname'].strip(),
        birth_date,
        street,
        house_number,
        row['PLZ'].strip(),
        row['Stadt'].strip(),
        row['Telefon'].strip(),
        row['Mobil'].strip(),
        row['Telefax'].strip(),
        row['E-Mail'].strip(),
        newsletter
    )


def insert_row(conn, data):
    """Inserts a row of customer data into the database"""
    cursor = conn.cursor()
    cursor.execute("""
        INSERT INTO customers (
            id, salutation, title, first_name, last_name, birth_date,
            street, house_number, postal_code, city, phone, mobile, fax, email, newsletter
        )
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """, data)


# Connect to the database and create a cursor object
conn = connect_to_database()

# Import customer data from the CSV file
import_customers(conn)

# Commit changes and close the database connection
close_database_connection(conn)
