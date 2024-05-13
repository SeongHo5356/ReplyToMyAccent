"""from transformers import pipeline
import pandas as pd
from transformers import AutoConfig, AutoTokenizer


model_name = "beomi/kcgpt2"
config = AutoConfig.from_pretrained(model_name)
tokenizer = AutoTokenizer.from_pretrained(model_name)
tokenizer.pad_token = tokenizer.eos_token
model_path = "C:/Users/kjeng/Desktop/Study/Capstone/DeployML/DeployML/ConvertModel/"
nlg_pipeline = pipeline('text-generation',model=model_path, tokenizer=model_name)


def filter_generated_result(text):
      return text.split("[SEP]")[0]
  
def generate_text(pipe, text, target_style, num_return_sequences=5):
  text = f"{text}[{target_style}]"
  out = pipe(text, num_return_sequences=num_return_sequences,
             pad_token_id=tokenizer.eos_token_id,
             max_length=100)
  return [filter_generated_result(x['generated_text']) for x in out]


df = pd.read_csv("C:/Users/kjeng/Downloads/dataset2.tsv", sep="\t")
src_text = "어제 먹은 밥 맛있더라"
df2 = ['formal','informal', 'Naeng', 'emoticon']
df3 = ['emoticon']
for style in df.columns:
  print(generate_text(nlg_pipeline, src_text, style, num_return_sequences=1)[0])"""