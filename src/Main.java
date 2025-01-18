public class Main {
    static class CaesarCipher {
        private int shift;
        private String message;

        public CaesarCipher(int shift, String message) {
            this.shift = shift;
            this.message = message;
        }

       void decryption(String message, int shiftNumber) {
            StringBuilder new_str = new StringBuilder();
            int c;
            int y;

            for (int i = 0; i < message.length(); i++) {
                c = (message.charAt(i) - shiftNumber);
                y = (c - (int) message.charAt(i)) % 32 + (int) message.charAt(i);
                new_str.append((char) y);
            }

            System.out.println("Crypted: " + message + " into "+new_str.toString());
           this.message = new_str.toString();
        }

         void encryption(String message, int shiftNumber) {
            StringBuilder new_str = new StringBuilder();
            int c;
            int y;
            for (int i = 0; i < message.length(); i++) {
                c = (message.charAt(i) + shiftNumber);
                y = (c - (int) message.charAt(i)) % 32 + (int) message.charAt(i);
                new_str.append((char) y);
            }
            System.out.println("Encrypted: " + message + " into " + new_str.toString());
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            CaesarCipher cipher1 = new CaesarCipher(3, "House");
            cipher1.decryption(cipher1.message, cipher1.shift);
            cipher1.encryption(cipher1.message, cipher1.shift);
        });
        Thread thread2 = new Thread(() -> {
            CaesarCipher cipher2 = new CaesarCipher(12, "Walk");
            cipher2.decryption(cipher2.message, cipher2.shift);
            cipher2.encryption(cipher2.message, cipher2.shift);
        });
        Thread thread3 = new Thread(() -> {
            CaesarCipher cipher3 = new CaesarCipher(16, "Summer");
            cipher3.decryption(cipher3.message, cipher3.shift);
            cipher3.encryption(cipher3.message, cipher3.shift);
        });
        Thread thread4 = new Thread(() -> {
            CaesarCipher cipher4 = new CaesarCipher(27, "Autumn");
            cipher4.decryption(cipher4.message, cipher4.shift);
            cipher4.encryption(cipher4.message, cipher4.shift);
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.sleep(2000);
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
