/*

Copyright 2021 Microoled
Licensed under the Apache License, Version 2.0 (the “License”);
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an “AS IS” BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/
package com.activelook.activelooksdk.types;

final class PayloadDecoder {

    private final byte [] bytes;
    private int offset;

    PayloadDecoder(final byte [] bytes) {
        this.bytes = bytes;
        this.offset = 0;
    }

    boolean hasNext() {
        return this.offset < this.bytes.length;
    }

    byte readByte() {
        return this.bytes[this.offset++];
    }

    byte readByte(final int offset) {
        this.offset = offset;
        return this.readByte();
    }

    long readLong() {
        return this.readLong(8);
    }

    long readLong(final int size) {
        long result = 0l;
        final int end = size + this.offset;
        while (this.offset < end) {
            result <<= 8;
            result |= (this.bytes[this.offset++] & 0xFF);
        }
        return result;
    }

    long readLong(final int offset, final int size) {
        this.offset = offset;
        return this.readLong(size);
    }

    char readChar() {
        return (char) this.readLong(2);
    }

    char readChar(final int size) {
        return (char) this.readLong(size);
    }

    char readChar(final int offset, final int size) {
        return (char) this.readLong(offset, size);
    }

    short readShort() {
        return (short) this.readLong(2);
    }

    short readShort(final int size) {
        return (short) this.readLong(size);
    }

    short readShort(final int offset, final int size) {
        return (short) this.readLong(offset, size);
    }

    int readUInt() {
        return (int) this.readLong(4);
    }

    int readUInt(final int size) {
        return (int) this.readLong(size);
    }

    int readUInt(final int offset, final int size) {
        return (int) this.readLong(offset, size);
    }

    boolean readBoolean() {
        return this.readLong(1) != 0;
    }

    boolean readBoolean(final int size) {
        return this.readLong(size) != 0;
    }

    boolean readBoolean(final int offset, final int size) {
        return this.readLong(offset, size) != 0;
    }

}
