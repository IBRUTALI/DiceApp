# DiceApp

Мини игра, в которой игроку предстоит сражаться с монстрами.

## Быстрый обзор приложения:

<p align="center">
  <img src="https://github.com/IBRUTALI/DiceApp/assets/96013243/52d30de9-9cff-45a3-9855-c3f6692eb043" alt="DiceApp Demo" title="DiceApp Demo" width="250" height="525"/>
  <h4 align="center">DiceApp Demo</h4>
</p>

У игрока и монстров есть показатели урона и брони. Урон по существу наносится, исходя из показателей урона и брони, или не наносится(промах или непробитие), исходя из броска кубика.  
При выпадении на кубике числа от 1 до 4 засчитывается промах, от 5 до 6 - попадание. Кубиков может бросаться несколько в зависимости от разницы урона нападающего и брони защищающегося, но всегда бросается хотя бы один кубик.  
Игрок может восстанавливать здоровье с помощью зелий (4 шт.).

## Реализованный функционал:
- Splash экран.
- Возможность выборка аватара и имени при создании персонажа.
- Валидация полей.
- Рассчет урона по существам в зависимости от характеристик существ.
- Логирование действий существ(бросок кубика, урон, восстановление здоровья).
- Поддержка дневной/ночной тем.
- Шрифты, стили EditText, кнопок сделаны через стили и темы приложения.

## Технологии:
- Для навигации Jetpack Navigation.
- DI - Dagger2.
- Архитектура - MVVM.
- Glide для загрузки изображений.
