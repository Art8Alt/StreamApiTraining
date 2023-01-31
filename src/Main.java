import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = getAnimals();

        // Старый подход (Императивный)
/*
        List<Animal> predators = new ArrayList<>();

        for (Animal animal: animals) {
            if (animal.getClassification().equals(Classification.PREDATOR)){
                predators.add(animal);
            }
        }

        predators.forEach(System.out::println);
*/

        // Новый подход (Декларативный)

        // Filter
        List<Animal> predators = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .toList();
        predators.forEach(System.out::println);
        System.out.println("________________________________________________________");
        // Sort
        List<Animal> sorted = animals.stream()
                .sorted(Comparator.comparing(Animal::getAge).reversed())
                .toList();

        sorted.forEach(System.out::println);
        System.out.println("________________________________________________________");

        // All match
        boolean allMatch = animals.stream()
                .allMatch(animal -> animal.getAge()>10);
        System.out.println(allMatch);
        System.out.println("________________________________________________________");

        // Any match
        boolean anyMatch = animals.stream()
                .anyMatch(animal -> animal.getAge()>10);
        System.out.println(anyMatch);
        System.out.println("________________________________________________________");

        // None match
        boolean noneMatch = animals.stream()
                .noneMatch(animal -> animal.getName().equals("Медведь"));
        System.out.println(noneMatch);
        System.out.println("________________________________________________________");

        // Max
        animals.stream()
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);
        System.out.println("________________________________________________________");

        // Min
        animals.stream()
                .min(Comparator.comparing(Animal::getAge))
                .ifPresent(System.out::println);
        System.out.println("________________________________________________________");

        // Group
        Map<Classification, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassification));

        classificationListMap.forEach((classification, animals1) -> {
            System.out.println(classification);
            animals1.forEach(System.out::println);
            System.out.println();
        });
        System.out.println("________________________________________________________");

        Optional<String> oldestPredator = animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);
        oldestPredator.ifPresent(System.out::println);

    }

    private static List<Animal> getAnimals() {
        return List.of(
                new Animal ("Слон", 20, Classification.HERBIVORE),
                new Animal ("Лев", 10, Classification.PREDATOR),
                new Animal ("Гиена", 11, Classification.PREDATOR),
                new Animal ("Жираф", 7, Classification.HERBIVORE),
                new Animal ("Гибон", 35, Classification.OMNIVOROUS),
                new Animal ("Лошадь", 36, Classification.HERBIVORE),
                new Animal ("Рысь", 2, Classification.PREDATOR),
                new Animal ("Динозавр", 200, Classification.PREDATOR)
        );
    }
}
