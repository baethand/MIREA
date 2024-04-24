def remove_empty_columns(input_table):
    num_columns = len(input_table[0])
    columns_to_delete = set()

    # Поиск пустых столбцов
    for col in range(num_columns):
        is_empty = all(row[col] == "" or row[col] is None
                       for row in input_table)
        if is_empty:
            columns_to_delete.add(col)

    # Удаление пустых столбцов
    for row in input_table:
        for col_index in sorted(columns_to_delete, reverse=True):
            del row[col_index]

    return input_table


def get_date(date):
    date_parts = date.split("-")
    abc = date_parts[1] + "/" + date_parts[0][-2:]
    output_date_str = date_parts[2][-2:] + "/" + abc
    return output_date_str


def get_email(email):
    mail_parts = email.split("@")
    return mail_parts[-1]


def map_to_percent(response):
    return str(int(float(response) * 100)) + "%"


def main(input_table):
    input_table = remove_empty_columns(input_table)

    output_objects = []

    for row in input_table:
        if row[0]:
            date_email = row[0].split("|")
            date = get_date(date_email[0])
            email = get_email(date_email[1])
            value1 = map_to_percent(row[1])
            obj = {"date": date,
                   "value1": value1,
                   "email": email}
            if obj not in output_objects:
                output_objects.append(obj)

    transposed_table = [
        [obj[key] for obj in output_objects]
        for key in output_objects[0]
    ]

    return transposed_table




input_table = [
    [None, "", "2000-01-07|lelij55@rambler.ru", "0.15", "0.15"],
    ["", "", "1999-12-21|regan49@yandex.ru", "0.33", "0.33"],
    ["", "", None, "", "", "", ""],
    ["", "", "1999-11-19|bizidi98@mail.ru", "0.34", "0.34"],
    ["", "", "1999-11-19|bizidi98@mail.ru", "0.34", "0.34"],
    ["", "", "2001-05-18|disulberg39@rambler.ru", "0.49", "0.49"],
    ["", "", "", "", "", "", ""],
]

# Вывод результата
for obj in main(input_table):
    print(obj)