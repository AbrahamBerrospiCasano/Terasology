// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.benchmark.chunks.arrays;

import org.terasology.engine.world.chunks.blockdata.TeraArray;

/**
 * BenchmarkTeraArrayRead implements a simple read performance benchmark for tera arrays.
 */
public class BenchmarkTeraArrayRead extends BenchmarkTeraArray {

    public BenchmarkTeraArrayRead(TeraArray array) {
        super(array);
    }

    @Override
    public String getTitle() {
        return array.getClass().getSimpleName() + " read performance";
    }

    @Override
    public void run() {
        int tmp = 0;
        for (int y = 0; y < array.getSizeY(); y++) {
            for (int z = 0; z < array.getSizeZ(); z++) {
                for (int x = 0; x < array.getSizeX(); x++) {
                    tmp += array.get(x, y, z);
                    array.get(x, y, z);
                }
            }
        }
    }

}
