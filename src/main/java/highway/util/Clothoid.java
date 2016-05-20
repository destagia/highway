package highway.util;

/**
 * クロソイド曲線を表現するクラス
 * Created by shohei.miyashita on 5/20/16.
 */
public class Clothoid {

    // 以下はクロソイドを表現するパラメータであり、俺は何かわからん
    private double R1;
    private double R3;
    private double A;

    public Clothoid(double sr, double er, double a) {
        this.R1 = sr;
        this.R3 = er;
        this.A = a;
    }

    public double getClothoidX(double startx, double startvector, double length) {
        int f = 0;
        if (R3 == 0 && R1 > 0) f = 2;
        else if (R3 == 0 && R1 < 0) {
            f = 5;
            R1 = R1 * -1;
        } else if (R1 > 0 && R3 > R1) f = 2;
        else if (R1 > 0 && R3 < R1) f = 1;
        else if (R1 == 0 && R3 > 0) f = 3;
        else if (R1 == 0 && R3 < 0) f = 4;
        else if (R1 < 0 && R3 > R1) {
            f = 6;
            R1 = R1 * -1;
        } else if (R1 < 0 && R3 < R1) {
            f = 5;
            R1 = R1 * -1;
        }
        double L1 = 0, G1 = 0, L2 = 0, R2 = 0, G2 = 0, X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;

        if (f == 1 || f == 6) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1;
            L2 = L1 + length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6));
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));

            if (f == 6) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 2 || f == 5) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1 * -1;
            L2 = L1 - length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2 * -1;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6)) * -1;
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6)) * -1;
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            R2 = R2 * -1;

            if (f == 5) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 3 || f == 4) {
            L1 = 0;
            G1 = 0;
            L2 = length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = 0;
            Y1 = 0;
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 4) {
                G2 = G2 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        return Math.cos(startvector - G1) * (X2 - X1) - Math.sin(startvector - G1) * (Y2 - Y1) + startx;
    }

    public double getClothoidY(double starty, double startvector, double length) {
        int f = 0;
        if (R3 == 0 && R1 > 0) f = 2;
        else if (R3 == 0 && R1 < 0) {
            f = 5;
            R1 = R1 * -1;
        } else if (R1 > 0 && R3 > R1) f = 2;
        else if (R1 > 0 && R3 < R1) f = 1;
        else if (R1 == 0 && R3 > 0) f = 3;
        else if (R1 == 0 && R3 < 0) f = 4;
        else if (R1 < 0 && R3 > R1) {
            f = 6;
            R1 = R1 * -1;
        } else if (R1 < 0 && R3 < R1) {
            f = 5;
            R1 = R1 * -1;
        }
        double L1 = 0, G1 = 0, L2 = 0, R2 = 0, G2 = 0, X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;

        if (f == 1 || f == 6) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1;
            L2 = L1 + length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6));
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 6) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 2 || f == 5) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1 * -1;
            L2 = L1 - length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2 * -1;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6)) * -1;
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6)) * -1;
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            R2 = R2 * -1;
            if (f == 5) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 3 || f == 4) {
            L1 = 0;
            G1 = 0;
            L2 = length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = 0;
            Y1 = 0;
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 4) {
                G2 = G2 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        return Math.sin(startvector - G1) * (X2 - X1) + Math.cos(startvector - G1) * (Y2 - Y1) + starty;
    }

    //終点の向き
    public double getClothoidTheta(double startvector, double length) {
        int f = 0;
        if (R3 == 0 && R1 > 0) f = 2;
        else if (R3 == 0 && R1 < 0) {
            f = 5;
            R1 = R1 * -1;
        } else if (R1 > 0 && R3 > R1) f = 2;
        else if (R1 > 0 && R3 < R1) f = 1;
        else if (R1 == 0 && R3 > 0) f = 3;
        else if (R1 == 0 && R3 < 0) f = 4;
        else if (R1 < 0 && R3 > R1) {
            f = 6;
            R1 = R1 * -1;
        } else if (R1 < 0 && R3 < R1) {
            f = 5;
            R1 = R1 * -1;
        }
        double L1 = 0, G1 = 0, L2 = 0, R2 = 0, G2 = 0, X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;

        if (f == 1 || f == 6) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1;
            L2 = L1 + length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6));
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 6) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 2 || f == 5) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1 * -1;
            L2 = L1 - length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2 * -1;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6)) * -1;
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6)) * -1;
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            R2 = R2 * -1;
            if (f == 5) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 3 || f == 4) {
            L1 = 0;
            G1 = 0;
            L2 = length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = 0;
            Y1 = 0;
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 4) {
                G2 = G2 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        return startvector + G2 - G1;
    }

    //終点の曲線半径
    public double clothoidR(double startvector, double length, double R1, double R3, double A) {
        int f = 0;
        if (R3 == 0 && R1 > 0) f = 2;
        else if (R3 == 0 && R1 < 0) {
            f = 5;
            R1 = R1 * -1;
        } else if (R1 > 0 && R3 > R1) f = 2;
        else if (R1 > 0 && R3 < R1) f = 1;
        else if (R1 == 0 && R3 > 0) f = 3;
        else if (R1 == 0 && R3 < 0) f = 4;
        else if (R1 < 0 && R3 > R1) {
            f = 6;
            R1 = R1 * -1;
        } else if (R1 < 0 && R3 < R1) {
            f = 5;
            R1 = R1 * -1;
        }
        double L1 = 0, G1 = 0, L2 = 0, R2 = 0, G2 = 0, X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;

        if (f == 1 || f == 6) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1;
            L2 = L1 + length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6));
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 6) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 2 || f == 5) {
            L1 = A * A / R1;
            G1 = L1 / 2 / R1 * -1;
            L2 = L1 - length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2 * -1;
            X1 = L1 * (1 - L1 * L1 / 40 / R1 / R1 + Math.pow(L1, 4) / 3456 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 599040 / Math.pow(R1, 6)) * -1;
            Y1 = L1 * L1 / 6 / R1 * (1 - L1 * L1 / 56 / R1 / R1 + Math.pow(L1, 4) / 7040 / Math.pow(R1, 4)
                    - Math.pow(L1, 6) / 1612800 / Math.pow(R1, 6));
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6)) * -1;
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            R2 = R2 * -1;
            if (f == 5) {
                G1 = G1 * -1;
                G2 = G2 * -1;
                Y1 = Y1 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }

        if (f == 3 || f == 4) {
            L1 = 0;
            G1 = 0;
            L2 = length;
            R2 = A * A / L2;
            G2 = L2 / 2 / R2;
            X1 = 0;
            Y1 = 0;
            X2 = L2 * (1 - L2 * L2 / 40.0 / R2 / R2 + Math.pow(L2, 4) / 3456 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 599040 / Math.pow(R2, 6));
            Y2 = L2 * L2 / 6 / R2 * (1 - L2 * L2 / 56 / R2 / R2 + Math.pow(L2, 4) / 7040 / Math.pow(R2, 4)
                    - Math.pow(L2, 6) / 1612800 / Math.pow(R2, 6));
            if (f == 4) {
                G2 = G2 * -1;
                Y2 = Y2 * -1;
                R2 = R2 * -1;
            }
        }
        return R2;
    }
}
