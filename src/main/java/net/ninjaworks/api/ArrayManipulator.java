package net.ninjaworks.api;

import net.ninjaworks.microservices.pc.PC;

public class ArrayManipulator {

    public static String arrayToString(String[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : array) {
            if (!(s == null)) {
                stringBuilder.append(s);
            }
        }
        if(!stringBuilder.toString().equals("")) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static PC[] addElement(PC[] array, PC PC2Add) {
        // create a new array of size n+1
        PC[] newArr = new PC[array.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < array.length; i++)
            newArr[i] = array[i];

        newArr[array.length] = PC2Add;

        return newArr;
    }

    public static String[] addElement(String[] array, String String2Add) {
        // create a new array of size n+1
        String[] newArr = new String[array.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (int i = 0; i < array.length; i++)
            newArr[i] = array[i];

        newArr[array.length] = String2Add;

        return newArr;
    }
}
