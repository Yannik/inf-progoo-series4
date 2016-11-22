import acm.program.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

            // The powerset is a set of sets of type integer.
            Set<Set<Integer>> powerSet = new LinkedHashSet<Set<Integer>>();

            // increase maxInt by one, as the first place is the zero
            // digit
            maxInt++;

            /*
            myinteger is increased by one every iteration,
            until the N'th(+1) digit of myinteger becomes 1.
            On every iteration, it is checked which places of the
            integers byte-string is 1 or not and added to the set accordingly.
             */
            int myinteger = 0;
            while ((myinteger & (1 << maxInt)) == 0) {
                Set<Integer> set = new LinkedHashSet<Integer>();
                for (int place = 0; place < maxInt; place++) {
                    if ((myinteger & (1 << place)) > 0) {
                        set.add(place);
                    }
                }
                powerSet.add(set);
                myinteger++;
            }

            println(powerSet.toString().
                    replaceAll("\\[", "{").
                    replaceAll("\\]", "}")
            );
            break;
        }
    }
}
