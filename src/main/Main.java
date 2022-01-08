package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import database.Database;
import fileio.Input;
import fileio.InputLoader;
import simulation.output.Output;
import simulation.Round;
import simulation.Simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {
    private Main() {
        // Constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = Constants.OUTPUT_PATH
                    + file.getName().replaceAll("[^0-9]", "")
                    + Constants.FILE_EXTENSION;
            action(file.getAbsolutePath(), filepath);
        }

        Checker.calculateScore();
    }

    /**
     * Method that runs a given test
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1, final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Database database = Database.getInstance(input);
        Simulation simulation = new Simulation(database.getNumberOfYears(), new Round(database));

        simulation.run();

        Output output = new Output(simulation.getResults());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath2), output);

        database.clearDatabase();
    }
}
