public class Main {
    static class CaesarCipher {
        private int shift;
        private String message;

        public CaesarCipher(int shift, String message) {
            this.shift = shift;
            this.message = message;
        }

        void decryption(String message, int shiftNumber) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " decrypting: " + message);
                try {
                    StringBuilder new_str = new StringBuilder();
                    int c;
                    int y;

                    for (int i = 0; i < message.length(); i++) {
                        c = (message.charAt(i) - shiftNumber);
                        y = (c - (int) message.charAt(i)) % 32 + (int) message.charAt(i);
                        new_str.append((char) y);
                    }

                    System.out.println("Crypted: " + message + " into " + new_str.toString());
                    this.message = new_str.toString();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void encryption(String message, int shiftNumber) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " encrypting: " + message);
                try {
                    StringBuilder new_str = new StringBuilder();
                    int c;
                    int y;
                    for (int i = 0; i < message.length(); i++) {
                        c = (message.charAt(i) + shiftNumber);
                        y = (c - (int) message.charAt(i)) % 32 + (int) message.charAt(i);
                        new_str.append((char) y);
                    }
                    System.out.println("Encrypted: " + message + " into " + new_str.toString());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CaesarCipher cipher1 = new CaesarCipher(3, "House");
        CaesarCipher cipher2 = new CaesarCipher(12, "Walk");

        Thread thread1 = new Thread(() -> {
            synchronized (cipher1) {
                cipher1.decryption(cipher1.message, cipher1.shift);
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (cipher2) {
                    cipher2.encryption(cipher2.message, cipher2.shift);
                }
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (cipher2) {
                cipher2.decryption(cipher2.message, cipher2.shift);
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (cipher1) {
                    cipher1.encryption(cipher1.message, cipher1.shift);
                }
            }
        }, "Thread-2");
        thread1.start();
        thread2.start();
    }
}