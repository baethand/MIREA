public class Program {
    public static int foo(int x) {
        return x * 10 + 42;
    }

    public static void Main() {
        int result = foo(5);
        Console.WriteLine(result);
    }
}
