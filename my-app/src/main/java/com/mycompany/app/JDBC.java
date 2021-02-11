package com.mycompany.app;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {

    protected String url;
    protected String username;
    protected String password;
    protected Connection connection;
    protected Statement statement;

    public JDBC(String URL, String User, String Password){
        url = URL;
        username = User;
        password = Password;
        try {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getShops(){
        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Shops");
            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2) + "\t| " + result.getString(3));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public ArrayList<String> getMedGroups(){
        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Med_groups");
            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public ArrayList<String> getEmployees(){

        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Employees");
            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2) + "\t| " + result.getString(3) + "\t| " + result.getString(4));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public ArrayList<String> getMedicine(){

        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Medicine");
            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2) + "\t| " + result.getString(3));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public ArrayList<String> getSales(){

        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Sales");
            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2) + "\t| " + result.getString(3) + "\t| " + result.getString(4)+ "\t| " + result.getString(5));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public ArrayList<String> getAvailability(){

        ArrayList<String> ans = new ArrayList<>();
        try {
            ResultSet result = Query("select * from Availability");

            while (result.next()) {
                ans.add(result.getString(1) + "\t| " + result.getString(2) + "\t| " + result.getString(3) + "\t| " + result.getString(4));
            }
            result.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    public void AddShop(String id, String address, String name){
        try {
            statement.execute("Insert into Shops values ("+id+","+address+","+name+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddMedGroup(String id, String name){
        try {
            statement.execute("Insert into Med_groups values ("+id+","+name+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddEmployee(String id, String name, String position, String shopID){
        try {
            statement.execute("Insert into Employees values ("+id+","+name+","+position+","+shopID+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddMedicine(String id, String group_id, String name){
        try {
            statement.execute("Insert into Medicine values ("+id+","+group_id+","+name+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddSale(String med_id, String price,String date, String shop_id){
        try {
            statement.execute("Insert into Sales (shop_id, med_id, price, sale_date) values ("+shop_id+","+med_id+","+price+","+date+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddAvailability(String med_id, String quantity, String shop_id){
        try {
            statement.execute("Insert into Availability (shop_id, med_id, quantity) values ("+shop_id+","+med_id+","+quantity+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteShop(String id){
        try {
            statement.execute("Delete from Shops where shop_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteMedGroup(String id){
        try {
            statement.execute("Delete from Med_groups where group_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteEmployee(String id){
        try {
            statement.execute("Delete from Employees where emp_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteMedicine(String id){
        try {
            statement.execute("Delete from Medicine where med_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteSale(String id){
        try {
            statement.execute("Delete from Sales where sale_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteAvailability(String id){
        try {
            statement.execute("Delete from Availability where id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditShop(String id, String address, String name){
        try {
            statement.execute("Update Shops set adress="+address+",shop_name="+name+" where shop_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditMedGroup(String id, String name){
        try {
            statement.execute("Update Med_groups set group_name="+name+" where group_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditEmployee(String id, String name, String position, String shopID){
        try {
            statement.execute("Update Employees set emp_name="+name+", position="+position+", shop_id="+shopID+" where emp_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditMedicine(String id, String group_id, String name){
        try {
            statement.execute("Update Medicine set med_name="+name+", group_id="+group_id+" where med_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditSale(String id,String med_id, String price,String date, String shop_id){
        try {
            statement.execute("Update Sales set med_id="+med_id+", price="+price+", sale_date="+date+", shop_id="+shop_id+" where sale_id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditAvailability(String id, String med_id, String quantity, String shop_id){
        try {
            statement.execute("Update Availability set med_id="+med_id+", quantity="+quantity+", shop_id="+shop_id+" where id="+id+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet Query(String query){

        try{
            return statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void CloseConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}