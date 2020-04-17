package chapter5_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions =
                Arrays.asList(new Transaction(brian, 2011, 300),
                        new Transaction(raoul, 2012, 1000),
                        new Transaction(raoul, 2011, 400),
                        new Transaction(mario, 2012, 710),
                        new Transaction(alan, 2012, 950));

        System.out.println("1.------------------------------------------------------");

        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        for (Transaction transaction : collect) {
            System.out.println("transaction = " + transaction);
        }

        System.out.println("2.------------------------------------------------------");

        List<String> collect2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        for (String s : collect2) {
            System.out.println("s = " + s);
        }

        System.out.println("3.------------------------------------------------------");

        List<Trader> collect3 = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getTrader())
                .sorted(comparing(Trader::getName))
                .collect(toList());

        for (Trader trader : collect3) {
            System.out.println("trader = " + trader);
        }

        System.out.println("4.------------------------------------------------------");

        List<String> collect4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .collect(toList());

        for (String s : collect4) {
            System.out.println("s = " + s);
        }

        System.out.println("5.------------------------------------------------------");

        boolean isMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println("isMilan = " + isMilan);

        System.out.println("6.------------------------------------------------------");

        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .forEach(System.out::println);


        System.out.println("6.------------------------------------------------------");

        Optional<Transaction> max = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2);

        System.out.println("max = " + max.get().getValue());

        Optional<Integer> max2 = transactions.stream()
                .map(t -> t.getValue())
                .max(Integer::compareTo);

        System.out.println("max2 = " + max2.get());

        Optional<Integer> max3 = transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer::max);

        System.out.println("max3 = " + max3.get());


        System.out.println("7.------------------------------------------------------");

        Optional<Integer> min = transactions.stream()
                .map(t->t.getValue())
                .min(Integer::compareTo);

        System.out.println("min = " + min.get());
    }
}
