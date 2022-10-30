# Based on : https://www.youtube.com/watch?v=zk0MeJ7YIMc (NeuralNine)
# Unlicensed

from kivy.app import App
from kivy.core.window import Window
from kivy.uix.boxlayout import BoxLayout
import kivy

class Root(BoxLayout):

    def __init__(self):
        super(Root, self).__init__()


    def calc_symbol(self, symbol):
        self.calc_field.text += symbol

    def solve(self):
        calc = self.calc_field.text
        try:
            res = eval(calc)
            self.calc_field.text = str(res)
        except:
            self.calc_field.text = 'ERR'

    def back(self):
        try:
            self.calc_field.text = self.calc_field.text[:-1]
        except:
            pass

    def clear(self):
        self.calc_field.text = ''

class Calc(App):

    def build(self):
        return Root()

calc = Calc()
calc.run()