package com.mycompany.app;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        JDBC Pharmacy = new JDBC("jdbc:mysql://localhost:3306/Pharmacy","root","root");
        Scanner input= new Scanner(System.in);
        boolean work = true;
        while (work){
            System.out.println("Выберите действие:\n" +
                                "1.Отобразить таблицу\n" +
                                "2.Добавить запись\n" +
                                "3.Удалить запись\n" +
                                "4.Редактировать запись\n" +
                                "5.Выход");
            switch (input.nextLine().charAt(0)){
                case '1': System.out.println("Выберите таблицу:\n" +
                        "1.Аптеки\n" +
                        "2.Группы лекарств\n" +
                        "3.Лекарства\n" +
                        "4.Сотрудники\n" +
                        "5.Продажи\n" +
                        "6.Доступность лекарств");
                ArrayList<String> result;
                //Showing tables
                switch (input.nextLine().charAt(0)){
                    case '1': result = Pharmacy.getShops();
                        System.out.println("id \t| address \t| shop_name");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    case '2': result = Pharmacy.getMedGroups();
                        System.out.println("id \t| group_name ");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    case '3': result = Pharmacy.getMedicine();
                        System.out.println("id \t| group_id \t| med_name");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    case '4': result = Pharmacy.getEmployees();
                        System.out.println("id \t| emp_name \t| position \t| shop_id");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    case '5': result = Pharmacy.getSales();
                        System.out.println("id \t| med_id \t| price \t| sale_date \t| shop_id");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    case '6': result = Pharmacy.getAvailability();
                        System.out.println("id \t| med_id \t| quantity \t| shop_id");
                        for (String element:result) {
                            System.out.println(element);
                        }
                        System.out.println();
                        break;
                    default: System.out.println("Выберите правильный пункт меню");
                        break;
                }
                break;//case 1
                //Add lines
                case '2': System.out.println("Выберите таблицу:\n" +
                        "1.Аптеки\n" +
                        "2.Группы лекарств\n" +
                        "3.Лекарства\n" +
                        "4.Сотрудники\n" +
                        "5.Продажи\n" +
                        "6.Доступность лекарств");
                    switch (input.nextLine().charAt(0)){
                        case '1':
                            System.out.println("Введите адрес");
                            String address ='"'+input.nextLine()+'"';
                            System.out.println("Введите название");
                            String shop_name ='"'+input.nextLine()+'"';
                            Pharmacy.AddShop(address,shop_name);
                            break;
                        case '2':
                            System.out.println("Введите название");
                            String group_name ='"'+input.nextLine()+'"';
                            Pharmacy.AddMedGroup(group_name);
                            break;
                        case '3':
                            System.out.println("Введите id группы");
                            String med_group_id =input.nextLine();
                            System.out.println("Введите название");
                            String med_name ='"'+input.nextLine()+'"';
                            Pharmacy.AddMedicine(med_group_id,med_name);
                            break;
                        case '4':
                            System.out.println("Введите имя");
                            String emp_name ='"'+input.nextLine()+'"';
                            System.out.println("Введите должность");
                            String emp_position ='"'+input.nextLine()+'"';
                            System.out.println("Введите id аптеки");
                            String emp_shop_id =input.nextLine();
                            Pharmacy.AddEmployee(emp_name,emp_position,emp_shop_id);
                            break;
                        case '5':System.out.println("Введите id препарата");
                            String sale_med_id =input.nextLine();
                            System.out.println("Введите стоимость");
                            String sale_price = input.nextLine();
                            System.out.println("Введите дату в формате ГГГГ-ММ-ДД");
                            String sale_date ='"'+input.nextLine()+'"';
                            System.out.println("Введите id апетки");
                            String sale_shop_id =input.nextLine();
                            Pharmacy.AddSale(sale_med_id,sale_price,sale_date,sale_shop_id);
                            break;
                        case '6':System.out.println("Введите id препарата");
                            String av_med_id =input.nextLine();
                            System.out.println("Введите количество");
                            String av_quantity =input.nextLine();
                            System.out.println("Введите id аптеки");
                            String av_shop_id =input.nextLine();
                            Pharmacy.AddAvailability(av_med_id,av_quantity,av_shop_id);
                            break;
                    }
                    break;//case 2

                //Delete lines
                case '3': System.out.println("Выберите таблицу:\n" +
                        "1.Аптеки\n" +
                        "2.Группы лекарств\n" +
                        "3.Лекарства\n" +
                        "4.Сотрудники\n" +
                        "5.Продажи\n" +
                        "6.Доступность лекарств");
                    String id;
                    switch (input.nextLine().charAt(0)){
                        case '1': System.out.println("Введите id");
                        id = input.nextLine();
                        Pharmacy.DeleteShop(id);
                            break;
                        case '2':System.out.println("Введите id");
                            id = input.nextLine();
                            Pharmacy.DeleteMedGroup(id);
                            break;
                        case '3':System.out.println("Введите id");
                            id = input.nextLine();
                            Pharmacy.DeleteMedicine(id);
                            break;
                        case '4':System.out.println("Введите id");
                            id = input.nextLine();
                            Pharmacy.DeleteEmployee(id);
                            break;
                        case '5':System.out.println("Введите id");
                            id = input.nextLine();
                            Pharmacy.DeleteSale(id);
                            break;
                        case '6':System.out.println("Введите id");
                            id = input.nextLine();
                            Pharmacy.DeleteAvailability(id);
                            break;
                    }
                    break; //case 3

                //Edit lines
                case '4': System.out.println("Выберите таблицу:\n" +
                        "1.Аптеки\n" +
                        "2.Группы лекарств\n" +
                        "3.Лекарства\n" +
                        "4.Сотрудники\n" +
                        "5.Продажи\n" +
                        "6.Доступность лекарств");
                    switch (input.nextLine().charAt(0)){
                        case '1':System.out.println("Введите id");
                            String shop_id = input.nextLine();
                            System.out.println("Введите адрес");
                            String address ='"'+input.nextLine()+'"';
                            System.out.println("Введите название");
                            String shop_name ='"'+input.nextLine()+'"';
                            Pharmacy.EditShop(shop_id,address,shop_name);
                            break;
                        case '2':System.out.println("Введите id");
                            String group_id = input.nextLine();
                            System.out.println("Введите название");
                            String group_name ='"'+input.nextLine()+'"';
                            Pharmacy.EditMedGroup(group_id,group_name);
                            break;
                        case '3':System.out.println("Введите id");
                            String med_id = input.nextLine();
                            System.out.println("Введите id группы");
                            String med_group_id =input.nextLine();
                            System.out.println("Введите название");
                            String med_name ='"'+input.nextLine()+'"';
                            Pharmacy.EditMedicine(med_id,med_group_id,med_name);
                            break;
                        case '4':System.out.println("Введите id");
                            String emp_id =input.nextLine();
                            System.out.println("Введите имя");
                            String emp_name ='"'+input.nextLine()+'"';
                            System.out.println("Введите должность");
                            String emp_position ='"'+input.nextLine()+'"';
                            System.out.println("Введите id аптеки");
                            String emp_shop_id =input.nextLine();
                            Pharmacy.EditEmployee(emp_id,emp_name,emp_position,emp_shop_id);
                            break;
                        case '5':System.out.println("Введите id");
                            String sale_id =input.nextLine();
                            System.out.println("Введите id препарата");
                            String sale_med_id =input.nextLine();
                            System.out.println("Введите стоимость");
                            String sale_price = input.nextLine();
                            System.out.println("Введите дату в формате ГГГГ-ММ-ДД");
                            String sale_date ='"'+input.nextLine()+'"';
                            System.out.println("Введите id апетки");
                            String sale_shop_id =input.nextLine();
                            Pharmacy.EditSale(sale_id,sale_med_id,sale_price,sale_date,sale_shop_id);
                            break;
                        case '6':System.out.println("Введите id");
                            String av_id =input.nextLine();
                            System.out.println("Введите id препарата");
                            String av_med_id =input.nextLine();
                            System.out.println("Введите количество");
                            String av_quantity =input.nextLine();
                            System.out.println("Введите id аптеки");
                            String av_shop_id =input.nextLine();
                            Pharmacy.EditAvailability(av_id,av_med_id,av_quantity,av_shop_id);
                            break;
                    }
                    break; //case 4

                //Break
                case '5': work = false;
                Pharmacy.CloseConnection();
                    break; //case 5
                //Invalid input
                default: System.out.println("Выберите правильный пункт меню");
                    break;
            }
        }
    }
}
