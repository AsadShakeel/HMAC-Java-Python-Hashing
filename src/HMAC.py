import hmac
import hashlib
import base64


def create_hash(key, message, algorithm):
    secret_bytes = bytes(key, "utf-8")
    payload_bytes = bytes(message, "utf-8")
    return hmac.new(secret_bytes, msg=payload_bytes, digestmod=algorithm).digest()


def hash_and_convert_to_base64_string(key, message, algorithm):
    hash_obj = create_hash(key, message, algorithm)
    return base64.b64encode(hash_obj).decode("utf-8").strip()


secret_key = "This is the secret"
hash_message = "This is the hash message"
print(hash_and_convert_to_base64_string(secret_key, hash_message, hashlib.sha256))
