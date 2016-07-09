/*
 * This file is part of Adventure, licensed under the BSD 3-Clause License
 *
 * Copyright (c) SiboRoc <http://siboroc.net>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.sibomots.plugin.adventure.core.types;

public class Alignment  {
        public enum LawAndOrder
        {
                NEUTRAL(0),
                LAWFUL(1),
                CHAOTIC(2);

                int value;
                LawAndOrder(int value)
                {
                        this.value = value;
                }
        }

        public enum GoodAndEvil
        {
                GOOD(1),
                EVIL(-1),
                NEUTRAL(0);

                int value;
                GoodAndEvil(int value)
                {
                        this.value = value;
                }
        }
        public enum Type {
                NEUTRALITY(LawAndOrder.NEUTRAL, GoodAndEvil.NEUTRAL),
                NEUTRAL_GOOD(LawAndOrder.NEUTRAL, GoodAndEvil.GOOD),
                NEUTRAL_EVIL(LawAndOrder.NEUTRAL, GoodAndEvil.EVIL),
                LAWFUL_GOOD(LawAndOrder.LAWFUL, GoodAndEvil.GOOD),
                LAWFUL_NEUTRAL(LawAndOrder.LAWFUL, GoodAndEvil.NEUTRAL),
                LAWFUL_EVIL(LawAndOrder.LAWFUL, GoodAndEvil.EVIL),
                CHAOTIC_GOOD(LawAndOrder.CHAOTIC, GoodAndEvil.GOOD),
                CHAOTIC_NRUTRAL(LawAndOrder.CHAOTIC, GoodAndEvil.NEUTRAL),
                CHAOTIC_EVIL(LawAndOrder.CHAOTIC, GoodAndEvil.EVIL);

                LawAndOrder lawfulness;
                GoodAndEvil goodness;

                Type(LawAndOrder lawfulness, GoodAndEvil goodness)
                {
                    this.lawfulness = lawfulness;
                    this.goodness = goodness;
                }
        }
}

