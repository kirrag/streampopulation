import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.*;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {

		List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
		List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
		Collection<Person> persons = new ArrayList<>();

		for (int i = 0; i < 10_000_000; i++) {
			persons.add(new Person(
					names.get(new Random().nextInt(names.size())),
					families.get(new Random().nextInt(families.size())),
					new Random().nextInt(100),
					Sex.values()[new Random().nextInt(Sex.values().length)],
					Education.values()[new Random().nextInt(Education.values().length)]));
		}

		long minors = persons.stream()
				.filter(x -> x.getAge() < 18)
				.count();

		System.out.println("Количество несовршенолетних: " + minors);

		List<String> peopleOfMilitaryAge = persons.stream()
				.filter(x -> (x.getAge() >= 18 && x.getAge() <= 27 && x.getSex().equals("MAN")))
				.map(x -> x.getFamily())
				.collect(Collectors.toList());

		Collection<Person> peopleOfWorkingAge = persons.stream()
				.filter(x -> ((x.getAge() >= 18 && x.getAge() <= 60 && x.getSex().equals("WOMAN")
						&& x.getEducation().equals("HIGHER"))
						|| (x.getAge() >= 18 && x.getAge() <= 65 && x.getSex().equals("MAN")
								&& x.getEducation().equals("HIGHER"))))
				.sorted((x1, x2) -> x1.getFamily().compareTo(x2.getFamily()))
				.collect(Collectors.toList());
	}
}
