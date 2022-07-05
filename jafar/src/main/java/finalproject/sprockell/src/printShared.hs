import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 1) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , ReadInstr (DirAddr 17) 
    , Receive regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (DirAddr 17) 
    , Receive regA 
    , WriteInstr regA numberIO 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 12) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

