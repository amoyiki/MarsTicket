"""

    Selenium submit name to Mars on NASA
    :copyright: (c) 2021 by Amoyiki.
    :license: BSD, see LICENSE for details.

"""

import os

from selenium import webdriver
import selenium
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import time
from selenium.webdriver.support.select import Select


def openChrome():
    option = webdriver.ChromeOptions()
    option.add_argument('disable-infobars')
    current_dir = os.path.dirname(os.path.abspath(__file__))
    print(current_dir)
    prefs = {'profile.default_content_settings.popups': 0, 'download.default_directory': r'{}'.format(current_dir)}
    print(prefs)
    option.add_experimental_option('prefs', prefs)
    driver = webdriver.Chrome(chrome_options=option)

    return driver


def operationAuth(driver):
    url='https://mars.nasa.gov/participate/send-your-name/future'

    driver.get(url)

    first = driver.find_element_by_id("FirstName")
    # 模拟输入值
    first.send_keys("test")

    last = driver.find_element_by_id("LastName")
    last.send_keys("test")

    Select(driver.find_element_by_id("CountryCode")).select_by_value("CN")

    code = driver.find_element_by_id("PostalCode")
    code.send_keys("test")

    email = driver.find_element_by_id("Email")
    email.send_keys("test@mail.com")

    current_window = driver.current_window_handle

    driver.find_element_by_name("SearchSubmit").click()

    all_window = driver.window_handles
    for window in all_window:
        if window != current_window:
            driver.switch_to.window(window)
    current_window = driver.current_window_handle

    time.sleep(3)

    dl = driver.find_element_by_id("downloadTicket")
    dl.click()
    print('done ！')
    time.sleep(3)

    driver.quit()

if __name__ == '__main__':

    driver = openChrome()
    operationAuth(driver)
