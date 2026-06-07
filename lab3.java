/**
 * Лабораторна робота №3
 * Тема: Рядки в мові програмування Java
 *
 * Номер залікової книжки: 5103
 *
 * Варіант:
 *   C3  = 5103 % 3  = 0 → Тип текстових змінних: StringBuilder
 *   C17 = 5103 % 17 = 5 → Дія: надрукувати слова без повторень
 *                          у алфавітному порядку за першою літерою
 */
public class Lab3 {

    public static void main(String[] args) {

        // --- Вхідний текст ---
        StringBuilder text = new StringBuilder(
            "The sun rises in the east and sets in the west. "
            + "Every morning the birds sing beautiful songs. "
            + "The wind blows gently through the trees and flowers bloom."
        );

        try {
            if (text.length() == 0) {
                throw new IllegalArgumentException("Текст не може бути порожнім!");
            }

            // --- Розбиваємо текст на слова ---
            // Видаляємо розділові знаки та розбиваємо по пробілах
            StringBuilder cleaned = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (Character.isLetter(ch) || ch == ' ') {
                    cleaned.append(ch);
                } else {
                    cleaned.append(' ');
                }
            }

            // Розбиваємо на слова
            String[] parts = cleaned.toString().trim().split("\\s+");

            // --- Збираємо унікальні слова у масив (без повторень, без урахування регістру) ---
            StringBuilder[] unique = new StringBuilder[parts.length];
            int uniqueCount = 0;

            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }
                StringBuilder word = new StringBuilder(part.toLowerCase());
                boolean found = false;
                for (int i = 0; i < uniqueCount; i++) {
                    if (unique[i].toString().equals(word.toString())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    unique[uniqueCount++] = word;
                }
            }

            // --- Сортуємо за першою літерою (bubble sort) ---
            for (int i = 0; i < uniqueCount - 1; i++) {
                for (int j = 0; j < uniqueCount - i - 1; j++) {
                    if (unique[j].charAt(0) > unique[j + 1].charAt(0)) {
                        StringBuilder tmp = unique[j];
                        unique[j] = unique[j + 1];
                        unique[j + 1] = tmp;
                    }
                }
            }

            // --- Вивід результату ---
            System.out.println("=== Лабораторна робота №3 ===");
            System.out.println("Вхідний текст:");
            System.out.println(text);
            System.out.println();
            System.out.println("Унікальні слова в алфавітному порядку за першою літерою:");

            char currentLetter = 0;
            for (int i = 0; i < uniqueCount; i++) {
                char firstLetter = unique[i].charAt(0);
                if (firstLetter != currentLetter) {
                    currentLetter = firstLetter;
                    System.out.println("\n[" + Character.toUpperCase(currentLetter) + "]");
                }
                System.out.println("  " + unique[i]);
            }

            System.out.println("\n-----------------------------");
            System.out.println("Всього унікальних слів: " + uniqueCount);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка вхідних даних: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Помилка індексу: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачена помилка: " + e.getMessage());
        }
    }
}
