package stepdefinition;

import com.mycompany.Environment;
import com.mycompany.utility.VideoUtility;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestRunner {
    public static void main(String[] args) {
        Environment.clear();
        Class<?>[] classes = {Feature1Runner.class, Feature2Runner.class,Feature3Runner.class, Feature4Runner.class};

        // Run them in parallel
        final ExecutorService parallelRunner = Executors.newFixedThreadPool(classes.length);
        final List<Future<Void>> futures = new ArrayList<>();
        for (final Class<?> clazz : classes) {
            futures.add(parallelRunner.submit(() -> {
                JUnitCore.runClasses(clazz);
                return null;
            }));
        }

        // Wait for all runners to finish
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        parallelRunner.shutdown();
        try {
            Thread.sleep(6000);
            VideoUtility.downloadAllVideos();
        }
        catch (Exception e){

        }
        finally {
            System.exit(0);
        }



    }
}

