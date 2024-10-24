proc checkAssert*(condition: bool): string =
  try:
    assert condition
    return "Assertion passed"
  except AssertionError:
    return "Assertion failed"