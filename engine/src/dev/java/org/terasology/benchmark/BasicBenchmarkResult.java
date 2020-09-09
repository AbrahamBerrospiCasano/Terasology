// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.benchmark;


/**
 * BasicBenchmarkResult extends BenchmarkResult and adds three basic columns for pretty printing.
 */
public class BasicBenchmarkResult extends BenchmarkResult {

    public BasicBenchmarkResult(Benchmark benchmark) {
        super(benchmark);
        addColumn(new Column<BasicBenchmarkResult>(this, Alignment.RIGHT, "#") {
            @Override
            public String getValueInternal(int rep) {
                return String.valueOf(rep + 1);
            }
        });
        addColumn(new Column<BasicBenchmarkResult>(this, Alignment.RIGHT, "Repetitions") {
            @Override
            protected String getValueInternal(int rep) {
                return String.valueOf(owner.getRepetitions(rep));
            }
        });
        addColumn(new Column<BasicBenchmarkResult>(this, Alignment.RIGHT, "Time in ms") {
            @Override
            protected String getValueInternal(int rep) {
                return String.valueOf(owner.getRunTime(rep));
            }
        });
    }

}
