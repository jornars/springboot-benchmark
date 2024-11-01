package com.example.demo.controller;

import com.example.demo.service.BenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {

    @Autowired
    private BenchmarkService benchmarkService;

    @GetMapping("/run")
    public String runBenchmark(@RequestParam("iterations") int iterations) {
        Runnable task = () -> {
            // Beispiel-Aufgabe (z.B. eine einfache Schleife)
            for (int i = 0; i < iterations; i++) {
                fibonacci(20); // 20 ist ein Beispiel. Du kannst den Wert erhöhen, um die Berechnung zu erschweren.
            }
        };

        long duration = benchmarkService.runBenchmark(task);
        return "Benchmark completed in " + duration / 1_000_000 + " ms";
    }

    // Hilfsmethode
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
