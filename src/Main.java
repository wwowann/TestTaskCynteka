import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        File f = new File("input.txt");
        BufferedReader fin = new BufferedReader(new FileReader(f));
        String line;
        boolean bool = false;
        int count = 0;
        String[] value2 = new String[0];
        String[] value1 = new String[0];
        while ((line = fin.readLine()) != null) {
            if (isInteger(line)) {
                if (count > 0) {
                    value1 = value2;
                    count = 0;
                    bool = false;
                }
                value2 = new String[Integer.parseInt(line)];
            }
            if (bool) {
                value2[count++] = line;
            }
            bool = true;
        }
        String[] value3 = value1.length < value2.length ? new String[value2.length] : new String[value1.length];
        for (int i = 0; i < value3.length; i++) {
            value3[i] = value1.length - 1 < i ? null : value1[i];
        }
        FileWriter writer = new FileWriter("output.txt");
        for (String str : newArrayOut(value3, value2)) {
            writer.write(str + "\n");
        }
        writer.close();
    }

    static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String[] newArrayOut(String[] arr1, String[] arr2) {
        String[] arr3 = new String[arr1.length];
        boolean bool = false;
        int count = -1;
        if (arr1.length == 1 && arr2.length == 1) {
            arr3[0] = arr1[0] + ":" + arr2[0];//проверка на единственное значение в списках
            return arr3;
        }
        for (String s1 : arr1) {
            count++;
            for (String s2 : arr2) {
                if (compareString(s1, s2)) {
                    arr3[count] = s1 + ":" + s2;
                    bool = true;
                } else if (s1 == null) {
                    arr3[count] = s2 + ":?";
                } else if (!bool) arr3[count] = s1 + ":?";
            }
        }
        return arr3;
    }

    static boolean compareString(String s1, String s2) {
        if (s1 == null) return false;
        String[] s1Arr = s1.split(" ");
        String[] s2Arr = s2.split(" ");
        for (String str1 : s1Arr) {
            for (String str2 : s2Arr) {
                if (str1.length() <= 2 || str2.length() <= 2) continue;
                if (str2.toLowerCase().contains(str1.toLowerCase())) return true;
            }
        }
        return false;
    }
}