package com.tartur.capco.application.cli.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class CLICommand<T> {
    protected static final Scanner scanner = new Scanner(System.in);
    private final String label;
    private final String code;
    private final List<CLICommand<T>> childrenActions;

    @SafeVarargs
    protected CLICommand(String label, String code, CLICommand<T>... childrenActions) {
        this.label = label;
        this.code = code;
        this.childrenActions = Arrays.asList(childrenActions);
    }

    public String getLabel() {
        return label;
    }

    protected void showChildren() {
        childrenActions.forEach(t -> System.out.println(t.code + ". " + t.label));
    }

    protected Optional<CLICommand<T>> chooseChild() {
        String listOfCodes = childrenActions.stream()
                .map(t -> t.code).collect(Collectors.joining(" ou "));
        String code = request("\nVotre choix (%s): ", listOfCodes);
        return childrenActions.stream().filter(a -> a.code.equals(code)).findFirst();
    }

    protected void print(String message, Object... args) {
        System.out.printf(message, args);
    }

    protected String request(String message, Object... args) {
        System.out.printf(message, args);
        return scanner.nextLine().trim();
    }

    protected <O> O repeatTillValid(Supplier<O> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                print("Veuillez entrer une valeure valide! (%s)\n", e.getMessage());
            }
        }
    }

    public abstract T execute();

}
