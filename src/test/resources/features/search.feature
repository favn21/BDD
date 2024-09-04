Feature: Работа с поисковой строкой

  @search
  Scenario: Поиск товара и проверка результатов
    Given пользователь на главной странице Wildberries
    When он вводит "iPhone 13" в строку поиска
    Then он видит текст "По запросу iPhone 13 найдено"
    And первый фильтр - iPhone 13
    And применен фильтр "По популярности"
    And у первого устройства из списка бренд - Apple
    When он нажимает на крестик
    Then строка поиска становится пустой