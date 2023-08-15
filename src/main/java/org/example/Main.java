package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final String[] foods = {
            "Nasi Goreng  ", "Mie Goreng   ", "Nasi + Ayam  ", "Es Teh Manis ", "Es Jeruk     ",
    };

    static final int[] price = {
            15_000, 13_000, 18_000, 3_000, 5_000,
    };

    static boolean isShowListMenu = true;
    public static void main(String[] args) {
        short[] qtyPerFood = {
                0,0,0,0,0
        };

        while (isShowListMenu){
            Scanner scanner = new Scanner(System.in);

            showListMenu();
            System.out.println();
            byte pilihan = -1;
            try {
                System.out.print("Masukan pilihan => ");
                pilihan = scanner.nextByte();
            }catch (InputMismatchException e){
                scanner.next();
            }

            switch (pilihan){
                case 0:
                    System.out.println("Keluar aplikasi, terima kasih");
                    isShowListMenu = false;
                    scanner.close();
                    break;
                case 1:
                    qtyPerFood[0] = getQuantity(foods[0], price[0]);
                    break;
                case 2:
                    qtyPerFood[1] = getQuantity(foods[1], price[1]);
                    break;
                case 3:
                    qtyPerFood[2] = getQuantity(foods[2], price[2]);
                    break;
                case 4:
                    qtyPerFood[3] = getQuantity(foods[3], price[3]);
                    break;
                case 5:
                    qtyPerFood[4] = getQuantity(foods[4], price[4]);
                    break;
                case 99:
                    showConfirmation(qtyPerFood);
                    System.out.println();
                    byte pilihan1 = -1;
                    try {
                        System.out.print("Masukan pilihan => ");
                        pilihan1 = scanner.nextByte();
                    }catch (InputMismatchException e){
                        scanner.next();
                    }
                    switch (pilihan1){
                        case 1:
                            printStruc(qtyPerFood, getStrucName());
                            scanner.close();
                            isShowListMenu = false;
                            break;
                        case 2:
                            break;
                        case 0:
                            System.out.println("Keluar aplikasi, Terima kasih");
                            scanner.close();
                            isShowListMenu = false;
                            break;
                        default:
                            System.out.println("Pilihan tidak tersedia!");
                            break;
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Pilihan tidak tersedia");
                    System.out.println();
            }
        }
    }

    static void showListMenu(){
        System.out.println("============================");
        System.out.println(" Selamat datang di BinarFud");
        System.out.println("============================");
        System.out.println();
        System.out.println("Silahkan pilih makanan :");
        System.out.println("1. Nasi Goreng   | 15.000");
        System.out.println("2. Mie Goreng    | 13.000");
        System.out.println("3. Nasi + Ayam   | 18.000");
        System.out.println("4. Es Teh Manis  | 3.000");
        System.out.println("5. Es Jeruk      | 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0.  Keluar aplikasi");
    }

    static short getQuantity(String foodName, int foodPrice){
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================");

        System.out.println(foodName +"   | "+ foodPrice);
        System.out.println("(input 0 untuk kembali)");

        System.out.print("QTY => ");

        while (true){
            try {
                return scanner.nextShort();
            }catch (InputMismatchException e){
                System.out.println("Input tidak valid. Silahkan masukan jumlah yang valid!");
                System.out.print("QTY => ");
                scanner.next();
            }
        }
    }

    static void showConfirmation(short[] qtyPerFood){
        System.out.println("================================");
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println("================================");
        System.out.println();

        byte index = 0;
        short sumQty = 0;
        int sumPrice = 0;

        for (short qty: qtyPerFood) {
            if (qty != 0){
                System.out.println(foods[index] + "   "+ qty + "      "+ changeFormatNumber(qty * price[index]));
                sumQty += qty;
                sumPrice += qty * price[index];
            }
            index++;
        }
        System.out.println("-------------------------------+");
        System.out.println("Total           "+sumQty+"      "+ changeFormatNumber(sumPrice));

        System.out.println();
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
    }

    static void printStruc(short[] qtyPerFood, String fileName){
        try{
            File file = new File(fileName);
            if (file.createNewFile()){
                System.out.println("Struk berhasil dicetak");
            }

            FileWriter writer = new FileWriter(file);

            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("==========================================");
            bwr.newLine();
            bwr.write("BinarFud");
            bwr.newLine();
            bwr.write("==========================================");
            bwr.newLine();
            bwr.newLine();
            bwr.write("Terima kasih sudah memesan di BinarFud");
            bwr.newLine();
            bwr.newLine();
            bwr.write("Dibawah ini adalah pesanan anda");
            bwr.newLine();
            bwr.newLine();
            bwr.newLine();

            // rincian harga

            byte index = 0;
            short sumQty = 0;
            int sumPrice = 0;

            for (short qty: qtyPerFood) {
                if (qty != 0){
                    bwr.write(foods[index] + "   "+ qty + "      "+ changeFormatNumber(qty * price[index]));
                    bwr.newLine();
                    sumQty += qty;
                    sumPrice += qty * price[index];
                }
                index++;
            }
            bwr.write("-------------------------------+");
            bwr.newLine();
            bwr.write("Total           "+sumQty+"      "+ changeFormatNumber(sumPrice));

            // end rincian harga

            bwr.newLine();
            bwr.newLine();
            bwr.newLine();
            bwr.write("Pembayaran : BinarCash");
            bwr.newLine();
            bwr.write("==========================================");
            bwr.newLine();
            bwr.write("Simpan struk ini sebagai bukti pembayaran");
            bwr.newLine();
            bwr.write("==========================================");
            bwr.flush();
            bwr.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String getStrucName(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime + " - Struk Pembayaran.txt";
    }

    static String changeFormatNumber(int number){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#,###");

        return decimalFormat.format(number);
    }
}