package com.example.yahtzeeprobability;

public final class ProbabilityEngine {
    public static double[][] odds = new double[13][];
    public static double ONE_SIXTH = 1.0 / 6.0;
    public static double FIVE_SIXTH = 5.0 / 6.0;

    // choose formula: n choose k = n! / (n-k)!k!
    public static double Choose(int n, int k) {
        return Factorial(n) / (Factorial(n - k) * Factorial(k));
    }

    public static int Factorial(int n) {
        int temp = 1;
        for (int i = n; i > 1; i--) {
            temp *= i;
        }
        return temp;
    }

    public static double[][] YahtzeeProbabilities(int[] rolls) {
        odds[0] = new double[5];
        odds[1] = new double[5];
        odds[2] = new double[5];
        odds[3] = new double[5];
        odds[4] = new double[5];
        odds[5] = new double[5];

        odds[6] = new double[1];
        odds[7] = new double[1];

        odds[8] = new double[1];
        odds[9] = new double[1];
        odds[10] = new double[1];
        odds[11] = new double[1];
        odds[12] = new double[1];

        int[] rolledNumbers = ConvertRoll(rolls);

        NumberRolls(rolledNumbers, 0);
        NumberRolls(rolledNumbers, 1);
        NumberRolls(rolledNumbers, 2);
        NumberRolls(rolledNumbers, 3);
        NumberRolls(rolledNumbers, 4);
        NumberRolls(rolledNumbers, 5);

        int mostRolled = 1;
        int secondMostRolled = 1;

        for (int i = 0; i < rolledNumbers.length; i++) {
            if (rolledNumbers[i] > mostRolled) {
                secondMostRolled = mostRolled;
                mostRolled = rolledNumbers[i];
            }
            else if (rolledNumbers[i] > secondMostRolled) {
                secondMostRolled = rolledNumbers[i];
            }
        }

        ThreeOfAKind(mostRolled);
        FourOfAKind(mostRolled);
        FullHouse(mostRolled, secondMostRolled);

        return odds;
    }

    public static int[] ConvertRoll(int[] rolledNumbers) {
        int[] numbersRolled = new int[6];
        numbersRolled[rolledNumbers[0] - 1]++;
        numbersRolled[rolledNumbers[1] - 1]++;
        numbersRolled[rolledNumbers[2] - 1]++;
        numbersRolled[rolledNumbers[3] - 1]++;
        numbersRolled[rolledNumbers[4] - 1]++;
        return numbersRolled;
    }

    public static double ExactlyN(int amount, int rolls) {
        return Choose(rolls, amount) * Math.pow(ONE_SIXTH, amount) * Math.pow(FIVE_SIXTH, rolls - amount);
    }

    public static void NumberRolls(int[] rolls, int num) {
        int numberOfNumRolled = rolls[num];
        double[] probabilities = new double[5];

        switch (numberOfNumRolled) {
            case 0:
                probabilities[0] = ExactlyN(1, 5);
                probabilities[1] = ExactlyN(2, 5);
                probabilities[2] = ExactlyN(3, 5);
                probabilities[3] = ExactlyN(4, 5);
                probabilities[4] = ExactlyN(5, 5);
                break;
            case 1:
                probabilities[0] = 100;
                probabilities[1] = ExactlyN(1, 4);
                probabilities[2] = ExactlyN(2, 4);
                probabilities[3] = ExactlyN(3, 4);
                probabilities[4] = ExactlyN(4, 4);
                break;
            case 2:
                probabilities[0] = 100;
                probabilities[1] = 100;
                probabilities[2] = ExactlyN(1, 3);
                probabilities[3] = ExactlyN(2, 3);
                probabilities[4] = ExactlyN(3, 3);
                break;
            case 3:
                probabilities[0] = 100;
                probabilities[1] = 100;
                probabilities[2] = 100;
                probabilities[3] = ExactlyN(1, 2);
                probabilities[4] = ExactlyN(2, 2);
                break;
            case 4:
                probabilities[0] = 100;
                probabilities[1] = 100;
                probabilities[2] = 100;
                probabilities[3] = 100;
                probabilities[4] = ExactlyN(1, 1);
                break;
            case 5:
                probabilities[0] = 100;
                probabilities[1] = 100;
                probabilities[2] = 100;
                probabilities[3] = 100;
                probabilities[4] = 100;
                break;
        }

        odds[num] = probabilities;
        if (odds[11][0] < probabilities[4]) {
            odds[11][0] = probabilities[4];
        }
    }

    public static void ThreeOfAKind(int mostRolled) {
        switch (mostRolled) {
            case 1:
                odds[6][0] = ExactlyN(2, 4) + ExactlyN(3, 4) + ExactlyN(4, 4);
                break;
            case 2:
                odds[6][0] = ExactlyN(1, 3) + ExactlyN(2, 3) + ExactlyN(3, 3);
                break;
            case 3:
                odds[6][0] = 100;
                break;
            case 4:
                odds[6][0] = 100;
                break;
            case 5:
                odds[6][0] = 100;
                break;
        }
    }

    public static void FourOfAKind(int mostRolled) {
        switch (mostRolled) {
            case 1:
                odds[7][0] = ExactlyN(3, 4) + ExactlyN(4, 4);
                break;
            case 2:
                odds[7][0] = ExactlyN(2, 3) + ExactlyN(3, 3);
                break;
            case 3:
                odds[7][0] = ExactlyN(1, 2) + ExactlyN(2, 2);
                break;
            case 4:
                odds[7][0] = 100;
                break;
            case 5:
                odds[7][0] = 100;
                break;
        }
    }

    public static void FullHouse(int mostRolled, int secondMostRolled) {
        switch (mostRolled) {
            case 1:
                double rollAll = ExactlyN(3, 3) * ExactlyN(2, 2);
                double rollFour = ExactlyN(2, 2) * ExactlyN(2, 2);
                double rollThree = ExactlyN(2, 2) * ExactlyN(1, 1);
                odds[8][0] = ExactlyN(2, 4) + ExactlyN(3, 4) + ExactlyN(4, 4);
                break;
            case 2:
                if (secondMostRolled == 2) {
                    odds[8][0] = ONE_SIXTH + ONE_SIXTH;
                }
                else {
                    double roll3 = ExactlyN(2, 3) * ExactlyN(1, 3);
                    double roll2 = ExactlyN(1, 2) * ExactlyN(1, 2);
                    odds[8][0] = ExactlyN(1, 3) * ExactlyN(2, 3);
                }
                break;
            case 3:
                if (secondMostRolled == 2) {
                    odds[8][0] = 100;
                }
                else {
                    odds[8][0] = ONE_SIXTH;
                }
                break;
            case 4:
                odds[8][0] = ONE_SIXTH;
                break;
            case 5:
                odds[8][0] = 100;
                break;
        }
    }

    public static void ShortStraight() {

    }

    public static void LargeStraight() {

    }
}
