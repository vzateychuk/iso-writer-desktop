package ru.vez.iso.desktop.shared;

/**
 * There are types of ApplicationState data
 * */
public enum AppStateType {

    APP_PROD_MODE,      // Режим приложения: PROD / DEV
    ZIP_DIR,       // Путь к файлу DIR.zip открытый пользователем в форме "Документы"
    // NOTIFICATION,       // Сообщение о нотификации
    USER_DETAILS,       // Информация о текущем загруженном пользователе
    OPERATION_DAYS,     // Операционные дни и StorageUnits (FX)
    DOCUMENTS,          // Список документов загруженных из REESTR файла
    ISO_FILES_NAMES,    // Список загруженных ISO файлов
    REESTR,             // Данные реестра документов, загруженные из файла DIR.zip форма Документы
    SETTINGS            // Настройки приложения

}
