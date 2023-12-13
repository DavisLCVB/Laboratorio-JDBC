package com.labjdbc.main;

import java.util.Scanner;

import com.labjdbc.domain.systemdomain.UsersSystem;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersSystem usersSystem = new UsersSystem(scanner);
        usersSystem.menu();
    }
}
