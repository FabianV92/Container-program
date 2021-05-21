package com.fv.ContainerApp.Controller;

import com.fv.ContainerApp.Model.Container;
import com.fv.ContainerApp.Model.ContainerData;
import com.fv.ContainerApp.Model.Customer;


import java.io.*;
import java.util.*;

/**
 * The controller class extends from the ContainerData class and implements the interface CrudMethods
 * and receives the data from the ContainerData and handles the information via the methods down below
 *
 * @author Fabian Valerius
 * @version 1.0.2
 */
public class Controller extends ContainerData implements CrudMethods {


    /**
     * The method create asks the user for the customer name, the location, type of the container, volume and weight.
     * Also checks for digits or letters for specific input fields. Finally add the array list to the
     * ContainerData.containers ArrayList.
     */
    @Override
    public void create() {

        boolean loopBool = true;
        boolean addBool = false;
        List<Object> arrList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Scanner scLoc = new Scanner(System.in);
        Scanner scType = new Scanner(System.in);
        String lineSep = "----------------------------------------------------------------------------";
        String separator = "-";
        double convToDb;

        while (loopBool) {
            // Asking and checking if customer name are only letters
            System.out.println("Name of the Customer");
            String customerName = sc.nextLine().trim();
            if (customerName.matches(".*\\d.*")) {
                System.out.println("!! Only letters are for the customer name allowed. Please try again !!\n" + lineSep);
                break;
            }

            // Setting default container name with a random id
            String containerName = ("Container" + (int) (Math.random() * 100) + 1).trim();

            // Asking and checking if location name only contain letters
            System.out.println("Current location of the container");
            String location = scLoc.nextLine();
            if (location.matches(".*\\d.*")) {
                System.out.println("!! Only letters are for the location allowed. Please try again !!\n" + lineSep);
                break;
            }

            // Asking and checking if type of the container only contains letters
            System.out.println("Type of the container");
            String typeOfContainer = scType.nextLine().trim();
            if (typeOfContainer.matches(".*\\d.*")) {
                System.out.println("!! Only letters are for the type of the container allowed. Please try again !!\n" + lineSep);
                break;
            }

            // Checking if volume input only contains digits
            System.out.println("Volume of the container");
            String volume = sc.next().trim();
            try {
                convToDb = Double.valueOf(volume);
            } catch (NumberFormatException e) {
                System.out.println("!! Wrong input-type in the volume field. Please try again !!\n" +
                        lineSep);
                break;
            }


            // Checking if the weight input contains only digits
            System.out.println("Weight of the current container");
            String weight = sc.next().trim();
            try {
                double convWeight = Double.valueOf(weight);
            } catch (NumberFormatException e) {
                System.out.println("!! Wrong input-type in the weight field. Please try again !!\n" +
                        lineSep);
                break;
            }
            addBool = true; // Allowing containers to add the array list at the end


            System.out.println("Successful added the container\n" + lineSep);
            loopBool = false; // closing the while loop
            Customer cs = new Customer(customerName);
            Container ct = new Container
                    (containerName, location, true, randomDate(), volume, weight, typeOfContainer);
            arrList.add(cs.getCustomerName() + separator);
            arrList.add(ct.getContainerName() + separator);
            arrList.add(ct.getLocation() + separator);
            arrList.add(ct.getStatus() + separator);
            arrList.add(ct.getDeliveryDate() + separator);
            arrList.add(ct.getVolume() + separator);
            arrList.add(ct.getWeight() + separator);
            arrList.add(ct.getTypeOfContainer() + separator);
        }
        if (addBool) {
            StringBuilder sb = new StringBuilder();
            for (Object i : arrList) {
                sb.append(i);
            }
            containers.add(sb.toString());
        }
    }

    /**
     * The randomDate method generates a custom date in the year of 2022 with the java random method.
     *
     * @return returns a random generated date formatted String in the year of 2022
     */
    public String randomDate() {
        Random rnd = new Random();
        Date dt;
        long ms;

        ms = 46771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        dt = new Date(ms);
        String date = dt.toString();
        StringBuilder finalDate = new StringBuilder();
        date = date.replaceAll("[\\d]|(CEST)|[:]|(CET)", "").trim();
        int rdDay = rnd.nextInt((30) + 1);
        finalDate.append(date).append(" ").append(rdDay).append("th").append(" 2022");
        String formatDate = finalDate.toString().replaceAll(" ", "\\/");

        return formatDate.toString();
    }

    /**
     * The read method displays all the customer names from all containers and the user choose which container he wants
     * to read the information from. After choosing the container element the information will be converted to a String
     * and then converted to a string array. The string array will be via a for loop displayed
     */
    @Override
    public void read() {
        String chooseContainerTxt = "Please choose one of the following customers down below to access the container " +
                "information (Only the number is enough)";
        String lineSep = "----------------------------------------------------------------------------";
        String convToStr;
        String containerNmb;
        String[] convToArr;
        Scanner sc = new Scanner(System.in);

        // Displaying all costumer names from all containers
        System.out.println(chooseContainerTxt);
        for (int i = 0; i < containers.size(); i++) {
            convToStr = containers.get(i).toString();
            convToStr = convToStr.replaceAll("[,]|\\[|\\]", "").trim();
            convToArr = convToStr.split("-");
            System.out.println((i + 1) + ": " + convToArr[0]);
        }
        containerNmb = sc.nextLine().replaceAll(" ", "");

        // Using try and catch to handle the exceptions the user may cause
        try {
            int testIfNumber = Integer.parseInt(containerNmb);
            String[] strArr;
            String str;

            str = containers.get((testIfNumber - 1)).toString();
            str = str.replaceAll("[,]|\\[|\\]", "").trim();
            strArr = str.split("-");

            // Displaying the container information
            System.out.println("Customer Name: " + strArr[0]);
            System.out.println("Container Name: " + strArr[1]);
            System.out.println("Location: " + strArr[2]);
            System.out.println("Container type: " + strArr[7]);
            System.out.println("Container volume: " + strArr[5]+ " m²");
            System.out.println("Container weight: " + strArr[6]+" kg");
            System.out.println("Delivery date: " + strArr[4]);
            System.out.println("Status of the container: " + strArr[3]);
            System.out.println(lineSep +
                    "\nPress 0 to come back to the main menu");
            sc.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Number does not match of the following number/s of the menu.  " +
                    "Please try again!\n" + lineSep);
        } catch (NumberFormatException e) {
            System.out.println("You typed accidentally a letter instead of a number. Please try again !\n" + lineSep);
        }
    }

    /**
     * The update method will display all customer names from the container. The user choose a container und also choose
     * an option via switch statement. 1. Change name of the customer 2. Change the current location 3. Change container
     * status and 4. Back to the main menu.
     * The user input will be checked and exception handled. The Container array will be updated with the new user
     * input.
     */
    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerStatus = new Scanner(System.in);
        Scanner scannerLocation = new Scanner(System.in);
        Scanner scanCName = new Scanner(System.in);
        String lineSep = "\n----------------------------------------------------------------------------";
        String chooseContainerTxt = "Please choose one of the following containers down below(Only the number is enough)";
        String infoTxt = "Type one of the numbers of the following menu down below and press enter";
        String menuText = "\n1: Change name of the customer\n" +
                "2: Change the current location of the container\n" +
                "3: Change the Container status\n" +
                "4: Back to the main menu";
        String changeCustomerNameTxt = "Type the new Customer name and press Enter";
        String changeLocationTxt = "Type the new location of the container and press Enter";
        String changeStatusTxt = "Type '+' to set container to active and '-' to not active";
        String success = "successful changed !";
        String[] strArr;
        String str;
        String casenNmb;

        // Displaying all customer names from the containers
        System.out.println(chooseContainerTxt);
        for (int i = 0; i < containers.size(); i++) {
            str = containers.get(i).toString();
            strArr = str.split("-");
            System.out.println(((i + 1) + ": " + strArr[0]));
        }

        try {
            String scName = scanner.nextLine();
            // Checking user input and handle the input
            int exceptionTest = Integer.parseInt(scName);
            if (!((Integer.parseInt(scName)-1) < containers.size() && (Integer.parseInt(scName)-1) >= 0)) {
                throw new IndexOutOfBoundsException();
            }
            casenNmb = scName;

            System.out.println(infoTxt + menuText);

            scName = scanner.next();
            String menuNmb = scName;

            str = containers.get((Integer.parseInt(casenNmb) - 1)).toString();
            strArr = str.split("-");

            switch (Integer.parseInt(menuNmb)) {
                case 1: {

                    System.out.println("Current name : " + strArr[0]);
                    System.out.println(changeCustomerNameTxt);
                    scName = scanCName.nextLine();
                    strArr[0] = scName;
                    StringBuilder sb = new StringBuilder();
                    addSeparator(strArr);

                    for (String i : strArr) {
                        sb.append(i);
                    }
                    System.out.println(success);
                    containers.set((Integer.parseInt(casenNmb) - 1), sb.toString());
                    break;
                }
                case 2: {

                    System.out.println("Current location : " + strArr[2] + "\n" + changeLocationTxt);

                    String scLoc = scannerLocation.nextLine();
                    strArr[2] = scLoc;
                    StringBuilder sb = new StringBuilder();
                    addSeparator(strArr);

                    for (String i : strArr) {
                        sb.append(i);
                    }
                    containers.set((Integer.parseInt(casenNmb) - 1), sb.toString());
                    System.out.println(success);
                    break;

                }
                case 3: {
                    System.out.println("Current container status : " + strArr[3] + "\n" + changeStatusTxt);

                    String scSts = scannerStatus.next();
                    strArr[3] = (scSts.equals("+") ? "active" : "delivered");
                    StringBuilder sb = new StringBuilder();
                    addSeparator(strArr);
                    for (String i : strArr) {
                        sb.append(i);
                    }
                    containers.set((Integer.parseInt(casenNmb) - 1), sb.toString());
                    System.out.println(success);
                    break;
                }
                case 4: {
                    break;
                }
                default: {
                    System.out.println("Number does not match of the following number/s of the menu.  " +
                            "Please try again!" + lineSep);
                    break;
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("You accidentally typed a letter. Please try again and use one of the numbers of the menu" +
                    "down below." + lineSep);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Number does not match of the following number/s of the menu.  " +
                    "Please try again!\n" + lineSep);
        }
    }

    /**
     * The addSeparator method takes an String array list and concatenates each element with an separator and finally
     * returns the array.
     *
     * @param arrList receives a String array with the container information of one customer
     * @return returns a String array with modified elements(Added separator to each element)
     */
    public String[] addSeparator(String[] arrList) {
        String separator = "-";
        for (int i = 0; i < arrList.length; i++) {
            arrList[i] = arrList[i] + separator;
        }
        return arrList;
    }


    /**
     * The delete method displays all customer names. The user choose one of the following containers which he want to
     * delete. After successful deleting the chosen container from the container arraylist  a text 'Successful deleted'
     * will be displayed
     */
    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        String deleteTxt = "Choose one of the following container and type the number of the container to delete the " +
                "container.";
        String[] convToArr;
        String convToStr;
        String lineSep = "\n----------------------------------------------------------------------------";

        // Displaying all the costumer names from the containers
        System.out.println(deleteTxt);
        for (int i = 0; i < containers.size(); i++) {
            convToStr = containers.get(i).toString();
            convToStr = convToStr.replaceAll("[,]|\\[|\\]", "");
            convToArr = convToStr.split("-");
            System.out.println((i + 1) + ": " + convToArr[0]);
        }
        // Using a try and catch to handle exceptions
        try {
            int deleteNmb = sc.nextInt();
            containers.remove((deleteNmb - 1));
            System.out.println("Successful deleted");
            System.out.println(containers.size());
        } catch (InputMismatchException e) {
            System.out.println("You typed accidentally a letter instead of a number. Please try again !" + lineSep);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Number does not match of the following number/s of the menu.  " +
                    "Please try again!" + lineSep);
        }
    }

    /**
     * The save method will search for the folder 'Data' and the file 'data.dat' and saves the container array tho the
     * 'data.dat' file
     */
    public void save() {
        String filePath = "Data\\data.dat";
        File fileOut = new File(filePath);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOut));
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(containers);
            oos.close();
            oos.flush();
            bos.close();
            bos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The load method serchs for the 'data' folder and the 'data.dat' file and reads the ArrayList in the file.
     * The containers array will be assigned to the array list which is in the 'data.dat' file after starting the
     * application.
     */
    public void load() {
        String filePath = "Data\\data.dat";
        File fileIn = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(fileIn);
            ObjectInputStream ois = new ObjectInputStream(fis);
            containers = (ArrayList) ois.readObject();
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * The init method loads the load() method (assigning containers array list to the data.dat array list) and asks
     * the users via a switch case in a while loop about which action the user wants to do and executes one of the
     * methods up below of the Controller class and finally terminates and saves the array list into the data.dat if
     * case 5 takes action.
     */
    @Override
    public void init() {
        boolean loopBool = true;
        Scanner sc = new Scanner(System.in);
        String containerAppTxt = "   ______            __        _                                    \n" +
                "  / ____/___  ____  / /_____ _(_)___  ___  _____   ____ _____  ____ \n" +
                " / /   / __ \\/ __ \\/ __/ __ `/ / __ \\/ _ \\/ ___/  / __ `/ __ \\/ __ \\\n" +
                "/ /___/ /_/ / / / / /_/ /_/ / / / / /  __/ /     / /_/ / /_/ / /_/ /\n" +
                "\\____/\\____/_/ /_/\\__/\\__,_/_/_/ /_/\\___/_/      \\__,_/ .___/ .___/ \n" +
                "                                                     /_/   /_/      ";
        String lineSep = "----------------------------------------------------------------------------";
        String userTxt = "Type one of the numbers of the following menu and press enter\n" +
                "1. Choose a container and let me see the whole information of the container\n" +
                "2. Create a new container\n" +
                "3. Edit a container\n" +
                "4. Delete a container\n" +
                "5. Exit the program";
        int userInput;
        String userInputStr;
        String defaultTxt = "!! Please type one of the following numbers of the menu (1-5) !!\n" +
                lineSep;
        load(); // Assigning containers array list to the data.dat array list
        System.out.println(containerAppTxt);
        while (loopBool) {
            try {
                System.out.println(userTxt);
                userInputStr = sc.next();
                userInput = Integer.parseInt(userInputStr);
            } catch (NumberFormatException e) {
                userInput = 0;
            }
            switch (userInput) {
                case 1: {
                    if (containers.size() != 0) {
                        read();
                    } else {
                        System.out.println("!! No containers created !!\n" +
                                lineSep);
                    }
                    break;
                }
                case 2: {
                    create();
                    break;
                }
                case 3: {
                    if (containers.size() != 0) {
                        update();
                    } else {
                        System.out.println("!! No containers to update are available !!\n" +
                                lineSep);
                    }
                    break;
                }
                case 4: {
                    if (containers.size() != 0) {
                        delete();
                    } else {
                        System.out.println("!! No containers to delete are available !!\n" +
                                lineSep);
                    }
                    break;
                }
                case 5: {
                    save();
                    loopBool = false;
                    break;
                }
                default: {
                    System.out.println(defaultTxt);
                    break;
                }
            }
        }
    }
}


