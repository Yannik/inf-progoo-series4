import acm.program.*;

public class PowerSet extends ConsoleProgram {
    public void run() {
        while (true) {
            int maxInt = readInt("Please enter N (0 <= N <= 10): ");
            if (!(maxInt >= 0 && maxInt <= 10)) {
                println("Illegal number");
                continue;
            }
            print("The powerset of { 0");
            for (int i = 1; i <= maxInt; i++) {
                print(", " + i);
            }
            println(" } is");
            int myinteger = 0;
            // 111
            maxInt++;
            print("{ ");
            boolean firstOuter = true;
            while ((myinteger & (1 << maxInt)) == 0) {
                boolean firstInner = true;
                if (!firstOuter) {
                    print(", ");
                }
                firstOuter = false;
                print("{ ");
                for (int place = 0; place < maxInt; place++) {
                    if ((myinteger & (1 << place)) > 0) {
                        if (!firstInner) {
                            print(", ");
                        }
                        print(place);
                        firstInner = false;
                    }
                }
                print(" }");
                myinteger++;
            }
            print(" }");
            break;
        }
    }
}
