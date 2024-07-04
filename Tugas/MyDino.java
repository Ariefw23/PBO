public class MyDino {
    // Declare member variables
    public static String name = "Vill";
    public static String typeOfDino = "Spinosaurus";
    public static int age = 1000;
    public static String color = "Brown";
    public static String favoriteFood = "Meat";
    public static float weight = 7.6f;

    public static void main(String[] args) {
        displayIdentity();
        displaySound();
        displaySpeedSwimming();
        attractions();
    }

    public static void displayIdentity() {
        System.out.println("Name: " + name);
        System.out.println("Type of Dino: " + typeOfDino);
        System.out.println("Age: " + age);
        System.out.println("Color: " + color);
        System.out.println("Favorite Food: " + favoriteFood);
        System.out.println("Weight: " + weight + " Ton");
        System.out.println();
    }

    public static void displaySound() {
        System.out.println("Raaaaawwwwwwrrrrrr");
        System.out.println();
    }

    public static void displaySpeedSwimming() {
        System.out.println("Spinosaurus Speeds is 17 KM per hours");
        System.out.println();
    }

    public static void attractions() {
        System.out.println("Vill do flip");
        System.out.println();
    }
}